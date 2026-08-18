import java.util.Scanner;

public class TokenCalculator {
    private static final double WORDS_TO_TOKEN_RATIO = 1.33; // Trung bình 3 từ ≈ 4 tokens
    private static final int CONTEXT_WINDOW_LIMIT = 8192; // Giới hạn giả định của GPT-4 tiêu chuẩn

    public static int estimateTokens(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }
        String[] words = text.trim().split("\\s+");
        return (int) Math.ceil(words.length * WORDS_TO_TOKEN_RATIO);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== HỆ THỐNG KIỂM TRA CONTEXT WINDOW ===");
        System.out.print("Nhập đoạn văn bản prompt của bạn: ");
        String prompt = scanner.nextLine();

        int estimatedTokens = estimateTokens(prompt);
        System.out.println("-----------------------------------------");
        System.out.println("Số từ ước tính: " + prompt.trim().split("\\s+").length);
        System.out.println("Số lượng Token ước tính: " + estimatedTokens);
        
        if (estimatedTokens > CONTEXT_WINDOW_LIMIT) {
            System.out.println("CẢNH BÁO: Vượt quá giới hạn Context Window (" + CONTEXT_WINDOW_LIMIT + " tokens). Vui lòng cắt bớt nội dung!");
        } else {
            System.out.println("TRẠNG THÁI: Hợp lệ. Còn trống " + (CONTEXT_WINDOW_LIMIT - estimatedTokens) + " tokens trong Context Window.");
        }
        scanner.close();
    }
}