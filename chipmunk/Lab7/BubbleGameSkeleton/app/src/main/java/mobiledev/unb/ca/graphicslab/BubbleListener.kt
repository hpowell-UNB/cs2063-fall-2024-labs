package mobiledev.unb.ca.graphicslab

interface BubbleListener {
    fun onBubbleViewRemoved(bubbleView: BubbleView, wasPopped: Boolean = false)
}