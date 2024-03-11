import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;


class Covid {

    public static String monthThai(int numberMonth){
        String[] month = {"มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน",
                        "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"}; 
        return month[numberMonth-1]; 
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // String gender = scan.next();
        
        //birthday
        // LocalDate birthDay = LocalDate.of(2499-543, 3, 10); // เกิดวันเสาร์ที่ 10 มีนาคม พ.ศ.2499 // pass
        LocalDate birthDay = LocalDate.of(2500-543, 10, 8);
        // LocalDate birthDay = LocalDate.of(2562-543, 7, 1); // เกิดวันจันทร์ที่ 1 กรกฎาคม พ.ศ.2562 // pass 
        // LocalDate birthDay = LocalDate.of(2564-543, 1, 5); //  เกิดวันอังคารที่ 5 มกราคม พ.ศ.2564 //fix
        System.out.println("birthday = " + birthDay);

        // วันก่อนได้รับวัคซีน ก้อน 1 วัน เพื่อตรวจสอบอายุ
        LocalDate currenDate = LocalDate.of(2564-543, 5, 31);

        //calculate age
        Period age = Period.between(birthDay, currenDate);
        System.out.println("อายุ year = " + age.getYears() + " month = " + age.getMonths() + " day = " + age.getDays());
            
       


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

            if (age.getYears() == 0 && age.getMonths() < 6){

                // บวกวันเกิดไป 6 เดือน เพื่อเช็ค ว่าอีกกี่วัน 6 เดือน
                LocalDate sixMonthsLaterBirthday = birthDay.plusMonths(6);
                System.out.println(sixMonthsLaterBirthday);

                //จำนวนวัน ที่จะอีก 6 เดือน
                Period numberOfDaysIn6Month = Period.between(birthDay, sixMonthsLaterBirthday);

                // บวกจำนวนวันที่เหลือกับวันเกิด
                LocalDate result = birthDay.plusDays(numberOfDaysIn6Month.getDays());
                result = result.plusMonths(numberOfDaysIn6Month.getMonths());
                result = result.plusYears(numberOfDaysIn6Month.getYears());
               
                // วันสุดท้ายที่เข้ารับ vacine
                LocalDate lastDateGetVacine = LocalDate.of(2021, 8, 31);
                System.out.println("result ==> "+ result); 

                if (result.isBefore(lastDateGetVacine)){
                    System.out.printf("เข้ารับบริการได้ตั้งแต่วันที่ %d %s พ.ศ. 2564 - 31 สิงหาคม พ.ศ. 2564\n", result.getDayOfMonth(), monthThai(result.getMonthValue()));
                }else if (result.isAfter(lastDateGetVacine)){ // อายุเกินรับวัคซีน 2 ปี ให้แสดง ว่าอีกี่ปีอายุครบ 65
                    //System.out.println("check"); //
                }



            }else {
                System.out.println("check"); 
            }

        }

    }
}