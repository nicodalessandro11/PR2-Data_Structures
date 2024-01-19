package uoc.ds.pr.util;

import uoc.ds.pr.CTTCompaniesJobsPR2.Level;

public class LevelHelper {
    public static Level getLevel(int experience) {
        if (experience <= 18) {
            return Level.INTERN;
        } else if (experience <= 499) {
            return Level.JUNIOR;
        } else if (experience <= 999) {
            return Level.SENIOR;
        } else if (experience <= 2500) {
            return Level.EXPERT;
        } else {
            return Level.BEGINNER;
        }
    }
}
