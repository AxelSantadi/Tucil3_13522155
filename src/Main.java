import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.File;

public class Main {
    public static Set<String> readWordListFromFile(String filePath) {
        Set<String> wordList = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                wordList.add(line.trim().toUpperCase()); // biar gampang, dibikin huruf besar semua
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }
    
    public static void main(String[] args) {
        int coba = 0;
        Scanner input = new Scanner(System.in);
        String startWord, endWord, pilihanLibrary;
        int choice;
        
        System.out.print("Pilih library yang ingin digunakan (pastikan ada di folder Library, dan tidak perlu memakai .txt): ");
        pilihanLibrary = input.nextLine();
        String path = "Library/" + pilihanLibrary + ".txt";

        //checker apakah file ada atau tidak di dalam folder Library
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("File tidak ditemukan di dalam folder Library.");
            System.exit(0);
        }
        Set<String> wordList = readWordListFromFile(path);

        // Input startWord dengan easter egg
        System.out.print("Masukkan startWord (Pastikan semua huruf besar): ");
        startWord = input.nextLine();
        while ((!isValidWord(startWord) || !wordList.contains(startWord)) && coba < 5) {
            coba++;
            System.out.println("Ups, katamu masih belum valid. Pastikan semua hurufnya adalah huruf besar, dan berbahasa inggris ya :3 Silakan coba lagi.");
            System.out.print("Masukkan startWord (Pastikan semua huruf besar): ");
            startWord = input.nextLine();
        } 

        if (coba == 5) {
            System.out.println("KOK KAMU SALAH TERUS SIH, KALO MAU KELUAR AJA DAH. BYE BYE!");
            System.exit(0);
        }

        coba = 0;
        // Input endWord dengan easter egg
        System.out.print("Masukkan endWord (Pastikan semua huruf besar): ");
        endWord = input.nextLine();
        while ((!isValidWord(endWord) || !wordList.contains(endWord) || startWord.length() != endWord.length()) && coba < 5) {
            coba++;
            System.out.println("Ups, katamu masih belum valid. Pastikan semua hurufnya adalah huruf besar, panjangnya sama dengan kata awal, dan berbahasa inggris ya :3 Silakan coba lagi.");
            System.out.print("Masukkan endWord (Pastikan semua huruf besar): ");
            endWord = input.nextLine();
        }

        if (coba == 5) {
            System.out.println("KOK KAMU SALAH TERUS SIH, KALO MAU KELUAR AJA DAH. BYE BYE!");
            System.exit(0);
        }


        // Memasukkan input untuk algoritma yang dipilih
        coba = 0;
        System.out.println("Pilih algoritma yang ingin digunakan: ");
        System.out.println("1. Uniform Cost Search");
        System.out.println("2. Greedy Best First Search");
        System.out.println("3. A*");
        System.out.print("Masukkan pilihan (1-3): ");

        
        // Input choice dengan easter egg
        while (true) {
            if (input.hasNextInt()) {
                choice = input.nextInt();
                if (choice >= 1 && choice <= 3) {
                    break;
                } else {
                    coba++;
                    System.out.println("Pilihan tidak valid. Silakan masukkan pilihan yang valid.");
                    System.out.print("Masukkan pilihan: ");
                }
            } else if (coba == 5) {
                System.out.println("KOK KAMU SALAH TERUS SIH, KALO MAU KELUAR AJA DAH. BYE BYE!");
                System.exit(0);
            } else {
                coba++;
                System.out.println("Pilihan tidak valid. Silakan masukkan pilihan yang valid.");
                System.out.print("Masukkan pilihan: ");
                input.next();
            }
        }
        
        long mulai = System.currentTimeMillis();

        // Proses algoritma sekaligus output
        switch (choice) {
            case 1:
                List<Object> ucsLadder = UCS.findWordLadderUCS(startWord, endWord, wordList);
                if (((List<?>) ucsLadder.get(0)).isEmpty()) {
                    System.out.println("Sayang sekali, tidak ditemukan ladder dari " + startWord + " ke " + endWord + " :(");
                } else {
                    System.out.println("Hasil Dari Uniform Cost Search Algorithm: " + ucsLadder.get(0));
                    System.out.println("Jumlah node yang dikunjungi: " + ucsLadder.get(1));
                }
                break;
            case 2:
                List<Object> greedyLadder = GBFS.findWordLadderGreedy(startWord, endWord, wordList);
                if (((List<?>) greedyLadder.get(0)).isEmpty()) {
                    System.out.println("Sayang sekali, tidak ditemukan ladder dari " + startWord + " ke " + endWord + " :(");
                } else {
                    System.out.println("Hasil dari Greedy Best First Search algorithm: " + greedyLadder.get(0));
                    System.out.println("Jumlah node yang dikunjungi: " + greedyLadder.get(1));
                }
                break;
            case 3:
                List<Object> aStarLadder = ABintang.findWordLadderABintang(startWord, endWord, wordList);
                if (((List<?>) aStarLadder.get(0)).isEmpty()) {
                    System.out.println("Sayang sekali, tidak ditemukan ladder dari " + startWord + " ke " + endWord + " :(");
                } else {
                    System.out.println("Hasil dari A* algorithm: " + aStarLadder.get(0));
                    System.out.println("Jumlah node yang dikunjungi: " + aStarLadder.get(1));
                }
                break;
        }
        long selesai = System.currentTimeMillis();
        System.out.println("Waktu eksekusi: " + (selesai - mulai) + " ms");

        // tutup the scanner
        input.close();
    }

    // pengecek kata apakah besar semua
    private static boolean isValidWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!Character.isUpperCase(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
