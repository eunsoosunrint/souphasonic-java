import net.yellowstrawberry.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SouphasonicTest {

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup(){
        System.setOut(new PrintStream(outContent) {
            @Override
            public PrintStream printf(String format, Object... args) {
                originalOut.printf(format, args);
                return super.printf(format, args);
            }

            @Override
            public void println(String x) {
                originalOut.println(x);
                originalOut.println();
                super.println(x);
            }
        });
    }

    @Test
    public void testcase1() {
        Main.process("언제든 Day and neight");
        assert outContent.toString().trim().equals("neight -> night\n" +
                "언제든 Day and night");
    }

    @Test
    public void testcase2() {
        Main.process("Show me rght now yaeh");
        assert outContent.toString().trim().equals("rght -> right\n" +
                "yaeh -> yeah\n" +
                "Show me right now yeah");
    }

    @Test
    public void testcase3() {
        Main.process("달라진 운도 차오른 설렘 Overloading");
        assert outContent.toString().trim().equals("운도 -> 온도\n" +
                "Overloading -> Overload\n" +
                "달라진 온도 차오른 설렘 Overload");
    }

    @Test
    public void testcase4() {
        Main.process("차 지금부터 뛰어들어 Ready sit you go");
        assert outContent.toString().trim().equals("차 -> 자\n" +
                "sit -> set\n" +
                "자 지금부터 뛰어들어 Ready set you go");
    }

    @Test
    public void testcase5() {
        Main.process("Heat and sweat 단숨에 푸 세게 Blew");
        assert outContent.toString().trim().equals("푸 -> 후\n" +
                "Blew -> Blow\n" +
                "Heat and sweat 단숨에 후 세게 Blow");
    }

    @Test
    public void testcase6() {
        Main.process("We got that 이대러 Go with the flew");
        assert outContent.toString().trim().equals("이대러 -> 이대로\n" +
                "flew -> flow\n" +
                "We got that 이대로 Go with the flow");
    }

    @Test
    public void testcase7() {
        Main.process("Show me right now wo");
        assert outContent.toString().trim().equals("wo -> woo\n" +
                "Show me right now woo");
    }

    @Test
    public void testcase8() {
        Main.process("내 맘속 Delight");
        assert outContent.toString().trim().equals("내 -> 네\n" +
                "네 맘속 Delight");
    }

    @Test
    public void testcase9() {
        Main.process("Heat me heat me up");
        assert outContent.toString().trim().equals("Heat -> Hit\n" +
                "heat -> hit\n" +
                "Hit me hit me up");
    }

    @Test
    public void testcase10() {
        Main.process("Buy my side");
        assert outContent.toString().trim().equals("Buy -> By\n" +
                "By my side");
    }
}
