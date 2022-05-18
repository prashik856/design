const express = require("express")
const axios = require("axios")
const cors = require("cors")

// import redis
const Redis = require("redis")

// Create an instance of redis
const redisClient = Redis.createClient()
// to make it work, we need to make sure that redis is running in our local pc

const app = express()

const DEFAULT_EXPIRATION = 3600

app.use(cors())

app.get("/photos", async (req, res) => {
    const albumId = req.query.albumId
    const photos = await getOrSetCache(`photos?albumId=${albumId}`, async () => {
        const {data} = await axios.get(
            "https://jsonplaceholder.typicode.com/photos",
            {params: {albumId}}
        )
        return data
    })
    res.json(photos)
    
    /*
    // Before we make a query to our database, we need to check if this value is already available in our redis cluster
    // if yes, just return that value

    // Now, when we create the key, we need to make sure that it also stores the unique album id.
    redisClient.get(`photos?albumId=${albumId}`, async (error, photos) => {
        if(error){
            console.log(error)
        }
        if(photos != null){
            console.log("Cache hit")
            // Since the value return to us from redis is a json, we need to parse this value first before returning.
            return res.json(JSON.parse(photos))
        } else {
            console.log("Cache miss")
            // Run our api to application and store the response inside redis
            const {data} = await axios.get(
                "https://jsonplaceholder.typicode.com/photos",
                {params: {albumId}}
            )
            // Set with our expiration
            // Store our data as photos
            // first stringify the data value
            redisClient.setex(`photos?albumId=${albumId}`, DEFAULT_EXPIRATION, JSON.stringify(data))
            res.json(data)
        }
    })
    */
})

app.get("photos/:id", async (req, res) => {
    // Naming convension used in the industry
    const photos = await getOrSetCache(`photos:${req.params.id}`, async () => {
        const {data} = await axios.get(
            `https://jsonplaceholder.typicode.com/photos/${req.params.id}`
        )
        return data
    })
    res.json(photos)
})

function getOrSetCache(key, cb) {
    return new Promise((resolve, reject) => {
        redisClient.get(key, async (error, data) => {
            if (error) {
                reject(error)
            }
            if(data != null){
                return resolve(JSON.parse(data))
            }
            const freshData = await cb()
            redisClient.setex(key, DEFAULT_EXPIRATION, JSON.stringify(freshData))
            resolve(freshData)
        })
    })
}

app.listen(3000)

// /photos will usually take around 300ms to 400ms because there is a lot of data inside it.
// /photos/id will take around 70ms because the data is very less

