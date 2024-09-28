package net.yellowstrawberry;

import java.util.Scanner;

public class Main {
    private static final String[] lyrics = new String[] {
            "언제든 Day and night",
            "Hit me hit me up",
            "Hit me hit me up now oh",
            "네 맘속 Delight",
            "Show me right now yeah",
            "Show me right now woo",
            "Super Super",
            "Supersonic Supersonic uh",
            "By my side",
            "Show me oh Supersonic",
            "Heat and sweat 단숨에 후 세게 Blow",
            "We got that 이대로 Go with the flow",
            "달라진 온도 차오른 설렘 Overload",
            "자 지금부터 뛰어들어 Ready set you go",
    };

    private static final int[] words = new int[] {
            4,
            5,
            7,
            3,
            5,
            5,
            2,
            3,
            3,
            4,
            7,
            8,
            5,
            7
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        String ipt = scanner.nextLine();
        String ipt = "달라진 운도 차오른 설렘 Overloading";
        process(ipt);
    }

    public static void process(String ipt) {
        String[] a = ipt.split(" ");
        int closestIndex = Integer.MAX_VALUE;
        int closest = Integer.MAX_VALUE;
        int[] r = new int[0];
        for (int i = 0; i < words.length; i++) {
            if(a.length== words[i]) {
                int[] re = matchWordCount(a, lyrics[i].split(" "));
                if(re[0]-(a.length>3?2:1)>0) {
                    if(closest > re[1]) {
                        closest = re[1];
                        closestIndex = i;
                        r = re;
                    }
                }
            }
        }

        String[] b = lyrics[closestIndex].split(" ");
        short cur = 2;
        while (cur < 6 && r[cur] != -1) {
            System.out.printf("%s -> %s%n", a[r[cur]], b[r[cur]]);
            cur += 2;
        }
        System.out.println(lyrics[closestIndex]);
    }

    private static int[] matchWordCount(String[] a, String[] b) {
        int[] r = new int[]{0, 0, -1, -1, -1, -1};
        short c = 2;
        for (int i = 0; i < a.length; i++) {
            if (a[i].trim().equals(b[i].trim())) {
                r[0]++;
            }else {
                if(c < 5) {
                    r[c++] = i;
                    r[c++] = compareChars(a[i].toCharArray(), b[i].toCharArray());
                    r[1] += r[c-1];
                }
            }
        }

        return r;
    }

    private static int compareChars(char[] a, char[] b) {
        int c = 0;
        if(b.length > a.length) {
            char[] tmp = a;
            a = b;
            b = tmp;
        }
        for (int i = 0; i < b.length; i++) {
            if(a[i]==b[i]) {
                c++;
            }
        }
        return a.length - c;
    }
}