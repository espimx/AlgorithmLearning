package com.espimx.algo.kt.list

data class ListNode(
    var value: Int,
    var next: ListNode? = null
)

class SingleLinkedList(val arr: IntArray) {
    var head: ListNode? = null
    var size = 0

    init {
        head = createLinkedList()
    }

    private fun createLinkedList(): ListNode? {
        if (arr.isEmpty()) return null

        val head = ListNode(arr[0])
        var cur: ListNode? = head

        for (i in 1 until arr.size) {
            cur?.next = ListNode(arr[i])
            cur = cur?.next
        }
        size = arr.size
        return head
    }

    fun addFirst(e: Int) {
        val newNode = ListNode(e)
        newNode.next = head
        head = newNode
        size++
    }

    fun addLast(e: Int) {
        var cur = head
        while (cur?.next != null) {
            cur = cur.next
        }
        cur?.next = ListNode(e)
        size++
    }

    fun addElement(index: Int, e: Int) {
        if (index > size) {
            throw IndexOutOfBoundsException("index = ${index}, size: $size")
        }
        var cur = head
        for (i in 0 until index - 1) {
            cur = cur?.next
        }
        val newNode = ListNode(e)
        newNode.next = cur?.next
        cur?.next = newNode
        size++
    }

    fun remove(index: Int) {
        if (index > size) {
            throw IndexOutOfBoundsException("index = ${index}, size: $size")
        }
        when (index) {
            0 -> {
                head = head?.next
            }
            size -> {
                var cur = head
                while (cur?.next?.next != null) {
                    cur = cur.next
                }
                cur?.next = null
            }
            else -> {
                var cur = head
                for (i in 0 until index - 1) {
                    cur = cur?.next
                }
                cur?.next = cur.next?.next
            }
        }
        size--
    }

    fun printList() {
        var p: ListNode? = head
        while (p != null) {
            print(p.value)
            p = p.next
            if (p != null) {
                print(" -> ")
            }
        }
        println()
    }
}

fun main() {
    val arr = intArrayOf(2, 1, 3, 4, 5, 7, 8, 9)
    val linkedList = SingleLinkedList(arr)
    linkedList.printList()
    linkedList.addFirst(12)
    linkedList.printList()
    linkedList.addLast(13)
    linkedList.printList()
    linkedList.addElement(2, 15)
    linkedList.printList()
    linkedList.remove(2)
    linkedList.printList()
}