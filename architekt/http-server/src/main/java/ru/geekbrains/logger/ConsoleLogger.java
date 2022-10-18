package ru.geekbrains.logger;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsoleLogger implements Logger {
    private static ConsoleLogger INSTANCE = new ConsoleLogger();

    public static ConsoleLogger getInstance()
    {
        return INSTANCE;
    }

    @Override
    public void info(String msg) {
        System.out.println(msg);
    }
}
