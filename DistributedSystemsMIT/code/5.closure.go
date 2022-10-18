package main

import "sync"

func main() {
	var a string
	var wg sync.WaitGroup
	wg.Add(1)
	// This is a go routine
	// Inline function definition
	go func() {
		a = "hello world"
		wg.Done()
	}()
	wg.Wait()
	// a is updated inside the inline function.
	println(a)
}