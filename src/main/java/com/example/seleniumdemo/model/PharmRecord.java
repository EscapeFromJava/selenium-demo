package com.example.seleniumdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmRecord {

    private String name;
    private String pharm;
    private String address;
    private String date;
    private String pricefull;

    @Override
    public String toString() {
        return "Наименование: " + name + '\n' +
                "Аптека: " + pharm + '\n' +
                "Адрес: " + address + '\n' +
                "Прайс: " + date + '\n' +
                "Цена: " + pricefull;
    }
}
