package test;

import java.util.Base64;

public class Decryptor {
    public static void main(String[] args) {
        String encryptedFI = "PEFjY291bnQgdHlwZT0iZGVwb3NpdCIgbWFza2VkQWNjTnVtYmVyPSJYWFhYWFhYMzQ2OCIgdmVyc2lvbj0iMS4wIiBsaW5rZWRBY2NSZWY9ImY2YjE0ODJlLThmMDgtMTFlOC04NjJhLTAyNTUyYjBkM2MzNiI-Cgk8UHJvZmlsZT4KCQk8SG9sZGVycz4KCQkJPEhvbGRlciBuYW1lPSJKb2huIERvZSIgdHlwZT0iU0lOR0xFIiBhYWRoYXI9IjEyMzQ1Njc4OTAxMiIgcGFuPSJBQkNERTEyMzRGIiBlbWFpbD0iam9obi5kb2VAZXhhbXBsZS5jb20iLz4KCQk8L0hvbGRlcnM-Cgk8L1Byb2ZpbGU-Cgk8U3VtbWFyeSBjdXJyZW50QmFsYW5jZT0iMTUwMDAuMDAiIGN1cnJlbmN5PSJJTlIiIGV4Y2hnZVJhdGU9IjEuMDAiPgoJCTxQZW5kaW5nPjA8L1BlbmRpbmc-Cgk8L1N1bW1hcnk-Cgk8VHJhbnNhY3Rpb25zPgoJCTxUcmFuc2FjdGlvbiBkYXRlPSIyMDIzLTEwLTAxIiB0eXBlPSJDUkVESVQiIG1vZGU9IkFUTSIgYW1vdW50PSI1MDAwLjAwIi8-CgkJPFRyYW5zYWN0aW9uIGRhdGU9IjIwMjMtMTAtMDIiIHR5cGU9IkRFQklUIiBtb2RlPSJVUEkiIGFtb3VudD0iMjAwMC4wMCIvPgoJPC9UcmFuc2FjdGlvbnM-CjwvQWNjb3VudD4";

        System.out.println("Original Base64 String: " + encryptedFI);

        // Clean the string
        String cleanedBase64 = encryptedFI.replaceAll("[^A-Za-z0-9+/=]", "");
        System.out.println("Cleaned Base64 String: " + cleanedBase64);

        try {
            byte[] encryptedData = Base64.getDecoder().decode(cleanedBase64);
            // Proceed with your decryption logic
            System.out.println("Decoded byte array length: " + encryptedData.length);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid Base64 string: " + e.getMessage());
        }
    }
}
