package ru.fomin.keycloak.annotations;

public class ThreadLocalService {
  public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
}
