package com.example.hackathon.util.state

interface StateListener {

    fun onStateChange(dataState: State<*>)
}