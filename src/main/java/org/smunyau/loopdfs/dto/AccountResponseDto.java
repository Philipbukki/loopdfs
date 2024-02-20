package org.smunyau.loopdfs.dto;

import lombok.Data;
import org.smunyau.loopdfs.entity.Account;
import java.util.List;

@Data
public class AccountResponseDto {

    private List<Account> content;

    private long pageNo;

    private long pageSize;

    private long totalElements;

    private long totalPages;

    private boolean last;
}
