package org.subhasht.practice.leetcode.problems;

public class DesignBoundedBlockingQueue_1188 {

    static class BoundedBlockingQueue {

        int [] arr;
        int head, rear, size;

        public BoundedBlockingQueue(int capacity) {
            arr = new int [capacity];
            head = 0;
            rear = 0;
            size = 0;
        }

        public void enqueue(int element) throws InterruptedException {
            //System.out.println("Enq: " + element);
            synchronized(this) {
                while(size() == arr.length) {
                    //System.out.println("Enq waiting");
                    wait();
                }
                if(head >= arr.length)
                    head = 0;
                arr[head++] = element;
                size++;
                //System.out.println("Enq head: " + head);

                notify();
            }
        }

        public int dequeue() throws InterruptedException {
            int e;
            //System.out.println("Deqing");
            synchronized(this) {
                while(size() == 0) {
                    //System.out.println("Deq waiting");
                    wait();
                }
                if(rear >= arr.length)
                    rear = 0;

                e = arr[rear++];
                size--;

                //System.out.println("Deq rear: " + rear);

                notify();
            }
            return e;
        }

        public int size() {
            synchronized(this) {
                return size;
            }
        }
    }
}
