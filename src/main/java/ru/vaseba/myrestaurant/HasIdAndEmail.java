package ru.vaseba.myrestaurant;

public interface HasIdAndEmail extends HasId {
    String getEmail();

    default String out() {
        return getId() + ":" + getEmail();
    }
}