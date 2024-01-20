package org.smunyau.loopdfs.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="accounts")
@NoArgsConstructor
@Getter @Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String iban;
    private String bicSwift;
    private Long clientId;
    @JsonManagedReference
    @OneToMany(mappedBy = "account",cascade= CascadeType.ALL)
    private List<Card> cards;


}
