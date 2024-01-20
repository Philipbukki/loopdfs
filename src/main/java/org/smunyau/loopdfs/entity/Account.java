package org.smunyau.loopdfs.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(hidden = true)
    private Long accountId;
    private String iban;
    private String bicSwift;
    private Long clientId;
    @JsonManagedReference
    @Schema(hidden = true)
    @OneToMany(mappedBy = "account",cascade= CascadeType.ALL)
    private List<Card> cards;


}
