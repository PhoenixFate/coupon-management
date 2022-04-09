package com.phoenix.core.filter;


public class AntPathMatcher implements PatternMatcher {
    public static final String DEFAULT_PATH_SEPARATOR = "/";
    private String pathSeparator = "/";

    public AntPathMatcher() {
    }

    public void setPathSeparator(String pathSeparator) {
        this.pathSeparator = pathSeparator != null ? pathSeparator : "/";
    }

    public boolean isPattern(String path) {
        return path.indexOf(42) != -1 || path.indexOf(63) != -1;
    }

    public boolean matches(String pattern, String source) {
        return this.match(pattern, source);
    }

    public boolean match(String pattern, String path) {
        return this.doMatch(pattern, path, true);
    }

    public boolean matchStart(String pattern, String path) {
        return this.doMatch(pattern, path, false);
    }

    protected boolean doMatch(String pattern, String path, boolean fullMatch) {
        if (path.startsWith(this.pathSeparator) != pattern.startsWith(this.pathSeparator)) {
            return false;
        } else {
            String[] pattDirs = StringUtils.tokenizeToStringArray(pattern, this.pathSeparator);
            String[] pathDirs = StringUtils.tokenizeToStringArray(path, this.pathSeparator);
            int pattIdxStart = 0;
            int pattIdxEnd = pattDirs.length - 1;
            int pathIdxStart = 0;

            int pathIdxEnd;
            String patDir;
            for(pathIdxEnd = pathDirs.length - 1; pattIdxStart <= pattIdxEnd && pathIdxStart <= pathIdxEnd; ++pathIdxStart) {
                patDir = pattDirs[pattIdxStart];
                if ("**".equals(patDir)) {
                    break;
                }

                if (!this.matchStrings(patDir, pathDirs[pathIdxStart])) {
                    return false;
                }

                ++pattIdxStart;
            }

            int patIdxTmp;
            if (pathIdxStart > pathIdxEnd) {
                if (pattIdxStart > pattIdxEnd) {
                    return pattern.endsWith(this.pathSeparator) ? path.endsWith(this.pathSeparator) : !path.endsWith(this.pathSeparator);
                } else if (!fullMatch) {
                    return true;
                } else if (pattIdxStart == pattIdxEnd && pattDirs[pattIdxStart].equals("*") && path.endsWith(this.pathSeparator)) {
                    return true;
                } else {
                    for(patIdxTmp = pattIdxStart; patIdxTmp <= pattIdxEnd; ++patIdxTmp) {
                        if (!pattDirs[patIdxTmp].equals("**")) {
                            return false;
                        }
                    }

                    return true;
                }
            } else if (pattIdxStart > pattIdxEnd) {
                return false;
            } else if (!fullMatch && "**".equals(pattDirs[pattIdxStart])) {
                return true;
            } else {
                while(pattIdxStart <= pattIdxEnd && pathIdxStart <= pathIdxEnd) {
                    patDir = pattDirs[pattIdxEnd];
                    if (patDir.equals("**")) {
                        break;
                    }

                    if (!this.matchStrings(patDir, pathDirs[pathIdxEnd])) {
                        return false;
                    }

                    --pattIdxEnd;
                    --pathIdxEnd;
                }

                if (pathIdxStart > pathIdxEnd) {
                    for(patIdxTmp = pattIdxStart; patIdxTmp <= pattIdxEnd; ++patIdxTmp) {
                        if (!pattDirs[patIdxTmp].equals("**")) {
                            return false;
                        }
                    }

                    return true;
                } else {
                    while(pattIdxStart != pattIdxEnd && pathIdxStart <= pathIdxEnd) {
                        patIdxTmp = -1;

                        int patLength;
                        for(patLength = pattIdxStart + 1; patLength <= pattIdxEnd; ++patLength) {
                            if (pattDirs[patLength].equals("**")) {
                                patIdxTmp = patLength;
                                break;
                            }
                        }

                        if (patIdxTmp == pattIdxStart + 1) {
                            ++pattIdxStart;
                        } else {
                            patLength = patIdxTmp - pattIdxStart - 1;
                            int strLength = pathIdxEnd - pathIdxStart + 1;
                            int foundIdx = -1;
                            int i = 0;

                            label148:
                            while(i <= strLength - patLength) {
                                for(int j = 0; j < patLength; ++j) {
                                    String subPat = pattDirs[pattIdxStart + j + 1];
                                    String subStr = pathDirs[pathIdxStart + i + j];
                                    if (!this.matchStrings(subPat, subStr)) {
                                        ++i;
                                        continue label148;
                                    }
                                }

                                foundIdx = pathIdxStart + i;
                                break;
                            }

                            if (foundIdx == -1) {
                                return false;
                            }

                            pattIdxStart = patIdxTmp;
                            pathIdxStart = foundIdx + patLength;
                        }
                    }

                    for(patIdxTmp = pattIdxStart; patIdxTmp <= pattIdxEnd; ++patIdxTmp) {
                        if (!pattDirs[patIdxTmp].equals("**")) {
                            return false;
                        }
                    }

                    return true;
                }
            }
        }
    }

    private boolean matchStrings(String pattern, String str) {
        char[] patArr = pattern.toCharArray();
        char[] strArr = str.toCharArray();
        int patIdxStart = 0;
        int patIdxEnd = patArr.length - 1;
        int strIdxStart = 0;
        int strIdxEnd = strArr.length - 1;
        boolean containsStar = false;
        char[] var14 = patArr;
        int strLength = patArr.length;

        int patLength;
        for(patLength = 0; patLength < strLength; ++patLength) {
            char aPatArr = var14[patLength];
            if (aPatArr == '*') {
                containsStar = true;
                break;
            }
        }

        char ch;
        int patIdxTmp;
        if (!containsStar) {
            if (patIdxEnd != strIdxEnd) {
                return false;
            } else {
                for(patIdxTmp = 0; patIdxTmp <= patIdxEnd; ++patIdxTmp) {
                    ch = patArr[patIdxTmp];
                    if (ch != '?' && ch != strArr[patIdxTmp]) {
                        return false;
                    }
                }

                return true;
            }
        } else if (patIdxEnd == 0) {
            return true;
        } else {
            while((ch = patArr[patIdxStart]) != '*' && strIdxStart <= strIdxEnd) {
                if (ch != '?' && ch != strArr[strIdxStart]) {
                    return false;
                }

                ++patIdxStart;
                ++strIdxStart;
            }

            if (strIdxStart > strIdxEnd) {
                for(patIdxTmp = patIdxStart; patIdxTmp <= patIdxEnd; ++patIdxTmp) {
                    if (patArr[patIdxTmp] != '*') {
                        return false;
                    }
                }

                return true;
            } else {
                while((ch = patArr[patIdxEnd]) != '*' && strIdxStart <= strIdxEnd) {
                    if (ch != '?' && ch != strArr[strIdxEnd]) {
                        return false;
                    }

                    --patIdxEnd;
                    --strIdxEnd;
                }

                if (strIdxStart > strIdxEnd) {
                    for(patIdxTmp = patIdxStart; patIdxTmp <= patIdxEnd; ++patIdxTmp) {
                        if (patArr[patIdxTmp] != '*') {
                            return false;
                        }
                    }

                    return true;
                } else {
                    while(patIdxStart != patIdxEnd && strIdxStart <= strIdxEnd) {
                        patIdxTmp = -1;

                        for(patLength = patIdxStart + 1; patLength <= patIdxEnd; ++patLength) {
                            if (patArr[patLength] == '*') {
                                patIdxTmp = patLength;
                                break;
                            }
                        }

                        if (patIdxTmp == patIdxStart + 1) {
                            ++patIdxStart;
                        } else {
                            patLength = patIdxTmp - patIdxStart - 1;
                            strLength = strIdxEnd - strIdxStart + 1;
                            int foundIdx = -1;
                            int i = 0;

                            label138:
                            while(i <= strLength - patLength) {
                                for(int j = 0; j < patLength; ++j) {
                                    ch = patArr[patIdxStart + j + 1];
                                    if (ch != '?' && ch != strArr[strIdxStart + i + j]) {
                                        ++i;
                                        continue label138;
                                    }
                                }

                                foundIdx = strIdxStart + i;
                                break;
                            }

                            if (foundIdx == -1) {
                                return false;
                            }

                            patIdxStart = patIdxTmp;
                            strIdxStart = foundIdx + patLength;
                        }
                    }

                    for(patIdxTmp = patIdxStart; patIdxTmp <= patIdxEnd; ++patIdxTmp) {
                        if (patArr[patIdxTmp] != '*') {
                            return false;
                        }
                    }

                    return true;
                }
            }
        }
    }

    public String extractPathWithinPattern(String pattern, String path) {
        String[] patternParts = StringUtils.tokenizeToStringArray(pattern, this.pathSeparator);
        String[] pathParts = StringUtils.tokenizeToStringArray(path, this.pathSeparator);
        StringBuilder buffer = new StringBuilder();
        int puts = 0;

        int i;
        for(i = 0; i < patternParts.length; ++i) {
            String patternPart = patternParts[i];
            if ((patternPart.indexOf(42) > -1 || patternPart.indexOf(63) > -1) && pathParts.length >= i + 1) {
                if (puts > 0 || i == 0 && !pattern.startsWith(this.pathSeparator)) {
                    buffer.append(this.pathSeparator);
                }

                buffer.append(pathParts[i]);
                ++puts;
            }
        }

        for(i = patternParts.length; i < pathParts.length; ++i) {
            if (puts > 0 || i > 0) {
                buffer.append(this.pathSeparator);
            }

            buffer.append(pathParts[i]);
        }

        return buffer.toString();
    }
}
