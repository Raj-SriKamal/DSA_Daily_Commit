
package DesignDataStructure;

import java.util.*;

public class BrowserHistory {

    ArrayList<String> history;
    int current;

    public BrowserHistory(String homepage) {
        history = new ArrayList<>();
        history.add(homepage);
        current = 0;
    }

    // Visit a new URL
    public void visit(String url) {

        // Remove forward history
        while (history.size() > current + 1) {
            history.remove(history.size() - 1);
        }

        // Add new URL
        history.add(url);

        // Move current pointer
        current++;
    }

    // Move backward
    public String back(int steps) {
        current = Math.max(0, current - steps);
        return history.get(current);
    }

    // Move forward
    public String forward(int steps) {
        current = Math.min(
            history.size() - 1,
            current + steps
        );

        return history.get(current);
    }

    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory("leetcode.com");

        browser.visit("google.com");
        browser.visit("facebook.com");
        browser.visit("youtube.com");

        System.out.println(browser.back(1));
        System.out.println(browser.back(1));
        System.out.println(browser.forward(1));

        browser.visit("linkedin.com");
        System.out.println(browser.forward(2));
        System.out.println(browser.back(2));
        System.out.println(browser.back(7));
    }
}
