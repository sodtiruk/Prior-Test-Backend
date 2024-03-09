import java.util.Scanner;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
class Covid {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String gender = scan.next();
        
        //birthday
        // LocalDate birthDay = LocalDate.of(2499-543, 3, 10); // เกิดวันเสาร์ที่ 10 มีนาคม พ.ศ.2499
        // LocalDate birthDay = LocalDate.of(2562-543, 7, 1); // เกิดวันจันทร์ที่ 1 กรกฎาคม พ.ศ.2562 
        LocalDate birthDay = LocalDate.of(2564-543, 1, 5); //  เกิดวันอังคารที่ 5 มกราคม พ.ศ.2564


        //currentDate to get vacine
        LocalDate currenDate = LocalDate.of(2564-543, 6, 1);

        //calculate age
        Period age = Period.between(birthDay, currenDate);
        System.out.println("year = " + age.getYears() + " month = " + age.getMonths() + " day = " + age.getDays());

        //เช็คว่าขาดอยู่อีกกี่วันถึงจะอายุ มากกว่า 6 เดือน แต่ไม่เกิน 2 ปี และยังอยู่ใน เดือนที่เขากำหนด

        if (age.getYears() >= 65) {
            System.out.printf("เข้ารับบริการได้ตั้งแต่วันที่ 1 มิถุนายน พ.ศ. 2564 - 31 สิงหาคม พ.ศ. 2564\n");
        }else {
            System.out.println("cannot");
        }


    }
}