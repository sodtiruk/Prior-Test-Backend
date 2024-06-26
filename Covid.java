import java.util.Scanner;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;

class Covid {
    // function สำหรับแปลงเดือนภาษาอังกฤษเป็นไทย
    public static String monthThai(int numberMonth){
        String[] month = {"มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน",
                        "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"}; 
        return month[numberMonth-1]; 
    }
    // function สำหรับแปลงวันภาษาอังกฤษเป็นไทย
    public static String dayThai(DayOfWeek day) {
        HashMap<DayOfWeek, String> weekDays = new HashMap<>() {{
            put(DayOfWeek.MONDAY, "จันทร์");
            put(DayOfWeek.TUESDAY, "อังคาร");
            put(DayOfWeek.WEDNESDAY, "พุธ");
            put(DayOfWeek.THURSDAY, "พฤหัสบดี");
            put(DayOfWeek.FRIDAY, "ศุกร์");
            put(DayOfWeek.SATURDAY, "เสาร์");
            put(DayOfWeek.SUNDAY, "อาทิตย์");
        }};
        return weekDays.get(day);
    }

    public static String genderThai(String gender) {
        if (gender.equals("male")) {
            return "ชาย";
        }else if (gender.equals("female")) {
            return "หญิง";
        }else {
            return  "เพศอื่นๆ";
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("กรุณากรอกเพศ เช่น male female");
        String gender = scan.next();
        System.out.println("กรุณาป้อนปีเกิดเป็น ปี(พ.ศ)-วัน-เดือน ตัวอย่าง 2499-10-3");
        String[] birthday = scan.next().split("-");
        LocalDate birthDayLocalDate = LocalDate.of(Integer.parseInt(birthday[0])-543, Integer.parseInt(birthday[2]), Integer.parseInt(birthday[1]));
        //birthday
        // LocalDate birthDay = LocalDate.of(2499-543, 3, 10); // เกิดวันเสาร์ที่ 10 มีนาคม พ.ศ.2499 2499-10-3
        // LocalDate birthDay = LocalDate.of(2500-543, 10, 8); // เกิดวันอังคารที่ 8 ตุลาคม พ.ศ.2500 2500-8-10
        // LocalDate birthDay = LocalDate.of(2562-543, 7, 1); // เกิดวันจันทร์ที่ 1 กรกฎาคม พ.ศ.2562 2562-1-7 
        // LocalDate birthDay = LocalDate.of(2564-543, 1, 5); //  เกิดวันอังคารที่ 5 มกราคม พ.ศ.2564 2564-5-1
        System.out.printf("%s เกิดวัน%sที่ %d %s พ.ศ.%d\n", genderThai(gender), dayThai(birthDayLocalDate.getDayOfWeek()), birthDayLocalDate.getDayOfMonth(), monthThai(birthDayLocalDate.getMonthValue()), birthDayLocalDate.getYear()+543);

        // วันก่อนได้รับวัคซีน ก้อน 1 วัน เพื่อตรวจสอบอายุ // 31 พฤษภาคม พ.ศ.2564
        LocalDate currenDate = LocalDate.of(2564-543, 5, 31);

        //คำนวนอายุ ระหว่างวันเกิด กับ ก่อนฉีดวัคซีนหนึ่งวัน
        Period age = Period.between(birthDayLocalDate, currenDate);
        // System.out.println("อายุ year = " + age.getYears() + " month = " + age.getMonths() + " day = " + age.getDays());
            
        if (age.getYears() >= 65) { // กรณีอายุ มากกว่า 65 สามารถเข้ารับวัคซีนได้เลย
            System.out.println("เข้ารับบริการได้ตั้งแต่วันที่ 1 มิถุนายน พ.ศ. 2564 - 31 สิงหาคม พ.ศ. 2564");
        }else if ( (age.getMonths() >= 6 && age.getYears() == 0) || (age.getYears() >= 1 && age.getYears() <= 2)){
            // บวกวันเกิดไป 2 ปี
            LocalDate numberOfDaysIn2Year = birthDayLocalDate.plusYears(2);

            // อีกกี่วันจะ 2 ปี
            Period howMany2Year = Period.between(currenDate, numberOfDaysIn2Year);
            // System.out.println("อีกกี่วัน 2 ปี " + howMany2Year.getYears() + "ปี " + howMany2Year.getMonths() + "เดือน " + howMany2Year.getDays() + "วัน");

            // กรณีทีเหลืออีก 1 เดือนกว่าๆ จะครบ 2 ปี เราไม่อยากให้อายุเกิน 2 ปีไม่งั้น จะไม่เข้าเงือนไขรับวัคซีน เลยต้องถอยช่วงรับวัคซีนมา 1 เดือน
            if (howMany2Year.getYears() == 0 && howMany2Year.getMonths() == 1 && howMany2Year.getDays() >= 1){
                System.out.println("เข้ารับบริการได้ตั้งแต่วันที่ 1 มิถุนายน พ.ศ. 2564 - 1 กรกฎาคม พ.ศ. 2564");
            }else {
                System.out.println("เข้ารับบริการได้ตั้งแต่วันที่ 1 มิถุนายน พ.ศ. 2564 - 31 สิงหาคม พ.ศ. 2564");
            }

        }else if (age.getYears() == 0 && age.getMonths() < 6){ // กรณีที่อายุน้อยกว่า 6 เดือน
                // บวกวันเกิดไป 6 เดือน เพื่อเช็ค ว่าอีกกี่วัน 6 เดือน
                LocalDate sixMonthsLaterBirthday = birthDayLocalDate.plusMonths(6);
                // System.out.println(sixMonthsLaterBirthday);

                //จำนวนวัน ที่จะอีก 6 เดือน
                Period numberOfDaysIn6Month = Period.between(birthDayLocalDate, sixMonthsLaterBirthday);

                // บวกจำนวนวันที่เหลือกับวันเกิด เพื่อเอาไปเช็คว่าเกินช่วงเสลารับวัคซีนวันสุดท้ายมั้ย
                LocalDate result = birthDayLocalDate.plusDays(numberOfDaysIn6Month.getDays());
                result = result.plusMonths(numberOfDaysIn6Month.getMonths());
                result = result.plusYears(numberOfDaysIn6Month.getYears());
               
                // System.out.println("result ==> "+ result); 

                // วันที่รับวัคซีนสันสุดท้าย 31 สิงหาคม พ.ศ.2564 บวกไป 1 วันเพราะวันที่ 31 ยังรับวัคซีนได้อยู่
                LocalDate lastDayGetVacine = LocalDate.of(2564-543, 9, 1);

                if (result.isBefore(lastDayGetVacine))  { // เช็คว่าอายุครบ 6 เดือน ก่อนรับยาวัคซีนวันสุดท้ายหรือมั้ย 
                    System.out.printf("เข้ารับบริการได้ตั้งแต่วันที่ %d %s พ.ศ. %d - 31 สิงหาคม พ.ศ. 2564\n", result.getDayOfMonth(), monthThai(result.getMonthValue()), result.getYear()+543);
                }else {
                    System.out.println("ไม่สามารถเข้ารับบริการได้ เนื่องจากเลยช่วงเวลารับ");
                }

        // กรณีอายุน้อย กว่า 65 แต่ มากกว่า 2 ปี ไม่สามารถเข้ารับวัคซีนได้ 
        }else { 
            // ปีที่จะอายุ 65 เราก็แค่บวกปีเกิดไป 65 แล้วบวก คศ 543 เพื่อแปลงเป็นพศ ก็จะได้ปีที่อายุ 65
            System.out.printf("ไม่สามารถเข้ารับบริการได้ เนื่องจากอายุจะครบ 65 ปี วันที่ %d %s พ.ศ. %d", birthDayLocalDate.getDayOfMonth(), monthThai((birthDayLocalDate.getMonthValue())), birthDayLocalDate.getYear()+65+543);
        }

        scan.close();

    }


}
