//
// Serial C
// Fetch the first page, extract all urls, and go the extracted urls and do the same.
// Ends when all the urls linked in a url are fetched.
// This function might end up in a cycle if we don't store already stored fetched.
//

// Serial crawler
// Performs a depth first search
/*
Goes to a page, fetches all the urls, and go through all fetched urls again.
We depend a lot on fetcher map. We need to send the reference of the map instead of
sending a copy of our map.
This does not do parallel fetches.

*/
func Serial(url string, fetcher Fetcher, fetched map[string]bool) {
	if fetched[url] {
		return
	}
	fetched[url] = true
	urls, err := fetcher.Fetch(url)
	if err != nil {
		return
	}
	for _, u := range urls {
		Serial(u, fetcher, fetched)
	}
}

type fetchState struct {
	mu sync.Mutex
	fetched map[string]bool
}

// Written with shared object and locks
// One of many ways to write a crawler.
func ConcurrentMutex(url string, fetcher Fetcher, f *fetchedState) {
	// locking
	f.mu.lock()

	already := f.fetched[url]
	f.fetched[url] = true

	// unlocking
	f.mu.unlock()

	if already {
		return
	}

	urls, err := fetcher.Fetch(url)
	if err != nil {
		return
	}

	var done sync.WaitGroup
	// loop over all urls
	for _, u := range urls {
		// Increments a wait group
		done.Add(1)

		// fire a go routine
		// declare a function write here that we call
		// Define a constant function which can be called. (Just like lambda?)
		go func(u string) {
			// This can be done to avoid errors in ConcurrentMutex function.
			// We always want to call done function whether the subrouting calls fails or runs successfully
			defer done.Done()

			ConcurrentMutex(u, fetcher, f)
			// This created a new thread for each url

			// decrements a wait group
			done.Done()
		} (u) // u is given as a parameter here, just like lambda
	}

	// This data structure help with coordination
	// Wait for a specific number of things to finish
	// Wait till value of wait group reaches 0
	done.Wait()

	// Why is'nt the two done statements have their own race conditions?
	// -> They might have their own lock statements


	return
}

// What will happen if we comment out the locks?
/*
We are going to see a fetch twice.
Go gives us a race detector.
Give -race parameter when running this go command.
This will give us an error saying that our Data is experiencing race.
This also gives us the line number where our race conditions are happening.
*/


// function using channels.
func makeState() *fetchState {

}

// This channel does not share memory and does not use locks
// The worker does not share objects.
func worker (url string, ch chan []string, fetcher Fetcher) {
	urls, err := fetcher.Fetch(url)
	if err != nil {
		ch <- []string{}
	} else {
		ch <- urls
	}
}

// This has a master function which fires off 1 go routine per url
// Only 1 master which is creating these threads
func master(ch chan []string, fetcher Fetcher) {
	n := 1
	// it's own match and it creates a single channel which all the workers will use
	fetched := make(map[string]bool)

	// Master is reading entries from the channel
	// workers are sending on channel and masters are reading from the channel
	// Channels are protected against concurrency.
	// This for loop is going to keep waiting till something shows up on the channel.
	for urls := range ch {
		// loops over the urls fetched by workers
		for _, u := range urls {
			if fetched[u] == false {
				fetched[u] = true
				n += 1
				go worker(u, ch, fetcher)
			}
		}
		n -= 1
		if n==0 {
			break
			// When crawl has completely finished.
			// When our number of workers goes to 0, we are done.
		}
	}
}

func ConcurrentChannel(url string, fetcher Fetcher) {
	ch := make(chan []string)
	// Shoves one url into channel
	// There better be someone in the channel because we need at least 1 worker for master to start.
	go func() {
		ch <- []string{url}
	} ()
	master(ch, fetcher)
}