package org.kamjeon.pcforge.PCpart;

public enum SearchType {
    COMCASE("COMCASE"),
    CPU("CPU"),
    DISK("DISK"),
    GPU("GPU"),
    MBOARD("MBOARD"),
    PSU("PSU"),
    RAM("RAM"),
    COMPANY("COMPANY"),
    ALL("ALL");

    private final String displayName;

    // 생성자
    SearchType(String displayName) {
        this.displayName = displayName;
    }

    // getter
    public String getDisplayName() {
        return displayName;
    }
}