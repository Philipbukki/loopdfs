package org.smunyau.loopdfs.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="accounts")
@NoArgsConstructor
@Getter @Setter
@Schema(
        name = "Accounts",
        description = "Table holding Account Details"

)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Schema(hidden = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "iban")
    private String iban;
    @NotBlank
    @Column(name = "bic_swift")
    private String bicSwift;
    @Column(name = "client_id")
    private Long clientId;
    @JsonManagedReference
    @Schema(hidden = true)
    @OneToMany(mappedBy = "account", cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
    private List<Card> cards;


}
