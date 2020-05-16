package space.platform.hackathon.util.state

interface StateListener {

    fun onStateChange(dataState: State<*>)
}