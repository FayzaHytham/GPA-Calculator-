package gpa;

import java.util.*;

public class gpacalc {
    private static final Map<String, Double> gradePoints = Map.ofEntries(
        Map.entry("A+", 4.00),
        Map.entry("A", 3.75),
        Map.entry("B+", 3.50),
        Map.entry("B", 3.00),
        Map.entry("C+", 2.50),
        Map.entry("C", 2.00),
        Map.entry("D+", 1.50),
        Map.entry("D", 1.00),
        Map.entry("F", 0.00)
    );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double totalQualityPoints = 0.0;
        double totalCredits = 0.0;

        // Add previous GPA support
        System.out.print("Do you have previous GPA to include? (yes/no): ");
        String hasPrevious = scanner.nextLine().trim().toLowerCase();

        if (hasPrevious.equals("yes")) {
            System.out.print("Enter previous cumulative GPA: ");
            double prevGPA = getValidatedDouble(scanner);
            System.out.print("Enter total credit hours counted in that GPA: ");
            double prevCredits = getValidatedDouble(scanner);

            totalQualityPoints += prevGPA * prevCredits;
            totalCredits += prevCredits;
        }

        System.out.print("\nEnter number of terms you want to add: ");
        int terms = getValidatedInt(scanner);

        for (int t = 1; t <= terms; t++) {
            System.out.println("\n--- Term " + t + " ---");
            System.out.print("Enter number of courses in term " + t + ": ");
            int courses = getValidatedInt(scanner);

            double termQualityPoints = 0.0;
            double termCredits = 0.0;
          //made by fayza hytham
            for (int c = 1; c <= courses; c++) {
                System.out.print("Course " + c + " name: ");
                String courseName = scanner.nextLine();

                double credit = 0.0;
                while (true) {
                    System.out.print("Credit hours: ");
                    try {
                        credit = Double.parseDouble(scanner.nextLine());
                        if (credit <= 0) {
                            System.out.println("Credit hours must be positive. Try again.");
                        } else {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a numeric value.");
                    }
                }

                System.out.print("Grade (A, B+, C+...): ");
                String grade = scanner.nextLine().toUpperCase();

                if (!gradePoints.containsKey(grade)) {
                    System.out.println("Invalid grade. Try again.");
                    c--;
                    continue;
                }
              //made by fayza hytham
                double points = gradePoints.get(grade);
                termQualityPoints += credit * points;
                termCredits += credit;
            }

            double termGPA = termQualityPoints / termCredits;
            System.out.printf("Term %d GPA: %.2f\n", t, termGPA);

            totalQualityPoints += termQualityPoints;
            totalCredits += termCredits;
        }

        double overallGPA = totalQualityPoints / totalCredits;
        System.out.printf("\nUpdated Overall GPA: %.2f\n", overallGPA);

        scanner.close();
    }

    // Helper method to safely get a valid int input
    private static int getValidatedInt(Scanner scanner) {
        int value;
        while (true) {
            try {
                value = Integer.parseInt(scanner.nextLine().trim());
                if (value <= 0) {
                    System.out.println("Please enter a positive number.");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        } //made by fayza hytham
    }

    // Helper method to safely get a valid double input
    private static double getValidatedDouble(Scanner scanner) {
        double value;
        while (true) {
            try {
                value = Double.parseDouble(scanner.nextLine().trim());
                if (value < 0) {
                    System.out.println("Please enter a non-negative number.");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
        //made by fayza hytham
    }
}
