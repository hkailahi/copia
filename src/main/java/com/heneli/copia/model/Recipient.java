package com.heneli.copia.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
public class Recipient extends User {

    @Id
    private int recipientId;
    private int restrictions;
    private int Sunday;
    private int Monday;
    private int Tuesday;
    private int Wednesday;
    private int Thursday;
    private int Friday;
    private int Saturday;
}
