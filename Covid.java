import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;


class Covid {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // String gender = scan.next();
        
        //birthday
        // LocalDate birthDay = LocalDate.of(2499-543, 3, 10); // เกิดวันเสาร์ที่ 10 มีนาคม พ.ศ.2499 // pass
        // LocalDate birthDay = LocalDate.of(2562-543, 7, 1); // เกิดวันจันทร์ที่ 1 กรกฎาคม พ.ศ.2562 // pass 
        LocalDate birthDay = LocalDate.of(2564-543, 1, 5); //  เกิดวันอังคารที่ 5 มกราคม พ.ศ.2564 //fix
        System.out.println("birthday = " + birthDay);

        // วันก่อนได้รับวัคซีน ก้อน 1 วัน เพื่อตรวจสอบอายุ
        LocalDate currenDate = LocalDate.of(2564-543, 5, 31);

        //calculate age
        Period age = Period.between(birthDay, currenDate);
        System.out.println("อายุ year = " + age.getYears() + " month = " + age.getMonths() + " day = " + age.getDays());
                



        //เช็คว่าขาดอยู่อีกกี่วันถึงจะอายุ มากกว่า 6 เดือน แต่ไม่เกิน 2 ปี และยังอยู่ใน เดือนที่เขากำหนด
        // >>>
        // บวกวันเกิดไป 6 เดือน เพื่อเช็ค ว่าอีกกี่วัน 6 เดือน
        LocalDate sixMonthsLaterBirthday = birthDay.plusMonths(6);
        System.out.println(sixMonthsLaterBirthday);

        //จำนวนวัน ที่จะอีก 6 เดือน
        Period numberOfDaysIn6Month = Period.between(currenDate, sixMonthsLaterBirthday);

        // บวกจำนวนวันที่เหลือกับวันเกิด
        LocalDate result = birthDay.plusDays(numberOfDaysIn6Month.getDays());
        result = result.plusMonths(numberOfDaysIn6Month.getMonths());
        result = result.plusYears(numberOfDaysIn6Month.getYears());
        


        if (age.getYears() >= 65) {
            System.out.println("เข้ารับบริการได้ตั้งแต่วันที่ 1 มิถุนายน พ.ศ. 2564 - 31 สิงหาคม พ.ศ. 2564");
        }else if ( (age.getMonths() >= 6 && age.getYears() == 0) || (age.getYears() >= 1 && age.getYears() <= 2)){
            // System.out.println("เข้ารับบริการได้ตั้งแต่วันที่ 1 มิถุนายน พ.ศ. 2564 - 1 กรกฎาคม พ.ศ. 2564");
            System.out.println("check");            
            
            // บวกวันเกิดไป 2 ปี
            LocalDate numberOfDaysIn2Year = birthDay.plusYears(2);

            // อีกกี่วันจะ 2 ปี
            Period Howmany2year = Period.between(currenDate, numberOfDaysIn2Year);
            System.out.println("อีกกี่วัน 2 ปี " + Howmany2year.getYears() + "ปี " + Howmany2year.getMonths() + "เดือน " + Howmany2year.getDays() + "วัน");
            if (Howmany2year.getYears() == 0){
                if (Howmany2year.getMonths() == 1 && Howmany2year.getDays() >= 1){
                    System.out.println("เข้ารับบริการได้ตั้งแต่วันที่ 1 มิถุนายน พ.ศ. 2564 - 1 กรกฎาคม พ.ศ. 2564");
                }else {
                    System.out.println("เข้ารับบริการได้ตั้งแต่วันที่ 1 มิถุนายน พ.ศ. 2564 - 31 สิงหาคม พ.ศ. 2564");
                }
            }else {
                System.out.println("เข้ารับบริการได้ตั้งแต่วันที่ 1 มิถุนายน พ.ศ. 2564 - 31 สิงหาคม พ.ศ. 2564");
            }
        }else {
            System.out.println("you cannot join");
        }

        System.out.println("day " + numberOfDaysIn6Month.getDays() + " month " + numberOfDaysIn6Month.getMonths() + " year " + numberOfDaysIn6Month.getYears());
        System.out.println("result " + result);

        Period between = Period.between(result, currenDate);
        System.out.println("between current date and result "+ between + " " + between.getDays() + " " + between.getMonths() + " " + between.getYears());
        System.out.println("");

    }
}