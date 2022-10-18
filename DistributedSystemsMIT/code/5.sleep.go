package main

import "time"

func main()  {
	time.Sleep(1 * time.Second)
	println("Started")
	// Code that periodically does something
	go periodic()
	time.Sleep(5 * time.Second) // Wait for a while so we can observe what ticker does
}

// A function with an infinite for loop which does something periodically
func periodic() {
	for {
		println("tick")
		time.Sleep(1 * time.Second)
	}
}