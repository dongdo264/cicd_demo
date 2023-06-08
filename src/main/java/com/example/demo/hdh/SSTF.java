package com.example.demo.hdh;

import java.util.*;

public class SSTF {
    public static void main(String[] args) {
        // Tạo một đĩa với 5.000 xi lanh được đánh số từ 0 đến 4.999
        int[] disk = new int[5000];
        for (int i = 0; i < 5000; i++) {
            disk[i] = i;
        }

        // Tạo một chuỗi ngẫu nhiên gồm 1.000 yêu cầu xi lanh
        Random rand = new Random();
        List<Integer> requests = new ArrayList<Integer>();
        for (int i = 0; i < 1000; i++) {
            requests.add(rand.nextInt(5000));
        }

        // Nhập vị trí ban đầu của đầu đĩa từ tham số dòng lệnh
//        int head_position = Integer.parseInt(args[0]);
        int head_position = rand.nextInt(5000);
        // Thực hiện thuật toán SSTF
        List<Integer> sstf_order = new ArrayList<Integer>();
        int total_head_movement_sstf = 0;
        while (!requests.isEmpty()) {
            int shortest_distance = Integer.MAX_VALUE;
            int shortest_index = -1;
            for (int i = 0; i < requests.size(); i++) {
                int distance = Math.abs(head_position - requests.get(i));
                if (distance < shortest_distance) {
                    shortest_distance = distance;
                    shortest_index = i;
                }
            }
            int next_request = requests.remove(shortest_index);
            sstf_order.add(next_request);
            total_head_movement_sstf += shortest_distance;
            head_position = next_request;
        }

        // In kết quả
        System.out.println("SSTF order: " + sstf_order);
        System.out.println("Total head movement (SSTF): " + total_head_movement_sstf);
    }
}
