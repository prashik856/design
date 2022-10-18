package main

import "sync"
import "time"

var done bool
var mu sync.Mutex

func main()  {
	time.Sleep(1 * time.Second)
	println("Started")
	go periodic1()
	time.Sleep(5 * time.Second) // wait for a while so we can observe what ticker does
	mu.Lock()
	done = true
	mu.Unlock()
	println("Cancelled")
	time.Sleep(3 * time.Second) // observe no output
}

func periodic1()  {
	for {
		println("tick")
		time.Sleep(1 * time.Second)
		mu.Lock()
		if done {
			mu.Unlock()
			return
		}
		mu.Unlock()
	}
}