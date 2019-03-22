package com.joe.reactive.reactclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEvent {
    private Employee employee;
    private Date date;

    @Override
    public String toString() {
        return "EmployeeEvent{" +
                "employee=" + employee.toString() +
                ", date=" + date +
                '}';
    }
}
