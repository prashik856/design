package main

import "sync"

func main() {
	var wg sync.WaitGroup
	// this loop spawns a bunch of go routines
	for i := 0; i<5; i++ {
		wg.Add(1)
		// These will be executed in parallel
		go func(x int) {
			// Send rpcs to all followers
			sendRPC(x)
			wg.Done()
		}(i) // i passed as argument
	}
	wg.Wait()
}

func sendRPC(i int)  {
	println(i)
}