package com.example.BaitapJava.Bai1;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Bookk> listBook = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        
        String menu = """
            1. Them 1 cuon sach
            2. Xoa 1 cuon sach theo ID
            3. Cap nhat sach
            4. Xuat danh sach thong tin
            5. Tim sach co tieu de chua 'Lap trinh'
            6. Lay toi da K cuon co gia <= P
            7. Tim kiem theo danh sach tac gia (Dung Set)
            0. Thoat
            Chon chuc nang:""";

        int chon = -1;
        do {
            System.out.print(menu);
            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so!");
                continue;
            }

            switch (chon) {
                case 1 -> {
                    Bookk b = new Bookk();
                    b.input();
                    listBook.add(b);
                }
                case 2 -> {
                    System.out.print("Nhap vao ma sach can xoa: ");
                    int idXoa = Integer.parseInt(sc.nextLine());
                    listBook.removeIf(b -> b.getId() == idXoa);
                    System.out.println("Da thuc hien xoa.");
                }
                case 3 -> {
                    System.out.print("Nhap vao ma sach can dieu chinh: ");
                    int bookId = Integer.parseInt(sc.nextLine());
                
                    listBook.stream()
                        .filter(p -> p.getId() == bookId)
                        .findFirst()
                        .ifPresentOrElse(
                            p -> {
                                System.out.println("Nhap thong tin moi cho sach");
                                p.input(); 
                                System.out.println("Cap nhat thanh cong!");
                            },
                            () -> System.out.println("Khong tim thay sach co ma " + bookId)
                        );
                }
                case 4 -> {
                    System.out.println("\nXUAT THONG TIN DANH SACH");
                    listBook.forEach(b -> b.output());
                }
                case 5 -> {
                    System.out.println("\nKET QUA TIM 'LAP TRINH'");
                    listBook.stream()
                        .filter(b -> b.getTitle().toLowerCase().contains("lap trinh") || 
                                     b.getTitle().toLowerCase().contains("lập trình"))
                        .forEach(Bookk::output); 
                }
                case 6 -> {
                    System.out.print("Nhap so luong toi da: ");
                    int k = Integer.parseInt(sc.nextLine());
                    System.out.print("Nhap muc gia toi da: ");
                    long p = Long.parseLong(sc.nextLine());

                    System.out.println("\nKET QUA LOC THEO GIA VA GIOI HAN");
                    listBook.stream()
                        .filter(b -> b.getPrice() <= p)
                        .limit(k)
                        .forEach(Bookk::output);
                }
                case 7 -> {
                    System.out.print("Nhap cac tac gia can tim: ");
                    String input = sc.nextLine();
                    Set<String> authorSet = Arrays.stream(input.split(","))
                                                  .map(String::trim)
                                                  .collect(Collectors.toSet());

                    System.out.println("\nSACH THEO NHOM TAC GIA");
                    listBook.stream()
                        .filter(b -> authorSet.contains(b.getAuthor()))
                        .forEach(Bookk::output);
                }
                case 0 -> System.out.println("Ket thuc chuong trinh!");
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
        
        sc.close();
    }
}