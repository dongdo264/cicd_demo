package com.example.demo.hdh;

import java.util.*;

public class DiskManagement {
    private List<Integer> memories_queue;
    private int start_item;
    private static int MAX_LENGTH = 1000;
    private static int MAX_SEQ = 5000;
    private List<Integer> sorted_lst;
    private List<Integer> seq_lst = new ArrayList<>();
    private int max_track;
    private boolean go_left = true;

    public DiskManagement(List<Integer> memories_queue, int max_track) {
        assert memories_queue.size() > 0;
        this.memories_queue = memories_queue;
        start_item = memories_queue.get(0);
        sorted_lst = new ArrayList<>();
        for (int i = 0; i < memories_queue.size(); i++) {
            sorted_lst.add(memories_queue.get(i));
        }
        Collections.sort(sorted_lst);
        this.max_track = max_track;
    }

    public void set_direction(boolean go_left) {
        this.go_left = go_left;
    }

    private void start_any_algorithm() {
        seq_lst = new ArrayList<>();
        System.out.println("Queue: " + memories_queue);
        for (Integer i: sorted_lst) {
            System.out.print(i + "\t");
        }
    }

    private void end_any_algorithm() {
        System.out.println();
        System.out.println("Sequence list: " + seq_lst);
    }

    private void print_state(int item) {
        System.out.println();
        for (Integer i: sorted_lst) {
            String content = i == item ? Integer.toString(i) : " ";
            System.out.print(content + "\t");
        }
    }

    public void sstf() {
        System.out.println("SSTF");
        start_any_algorithm();
        int current_item = start_item;
        for (int i = 0; i < memories_queue.size(); i++) {
            int min_distance = Integer.MAX_VALUE;
            int min_index = -1;
            for (int j = 0; j < memories_queue.size(); j++) {
                if (memories_queue.get(j) == -1) continue;
                int distance = Math.abs(current_item - memories_queue.get(j));
                if (distance < min_distance) {
                    min_distance = distance;
                    min_index = j;
                }
            }
            current_item = memories_queue.get(min_index);
            memories_queue.set(min_index, -1);
            print_state(current_item);
            seq_lst.add(current_item);
        }
        end_any_algorithm();
    }

    public static List<Integer> get_random_unique_list(int seq_len, int num_process) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < seq_len; i++) {
            int item = (int) (Math.random() * num_process);
            while (list.contains(item)) {
                item = (int) (Math.random() * num_process);
            }
            list.add(item);
        }
        return list;
    }

    public static void main(String[] args) {
        // List<Integer> memories_queue = get_random_unique_list(10, 100);
        List<Integer> memories_queue = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < MAX_LENGTH ; i++) {
            memories_queue.add(rand.nextInt(5000));
        }
        DiskManagement diskManagement = new DiskManagement(memories_queue, 500);
        diskManagement.set_direction(false);
        diskManagement.sstf();
    }
}
