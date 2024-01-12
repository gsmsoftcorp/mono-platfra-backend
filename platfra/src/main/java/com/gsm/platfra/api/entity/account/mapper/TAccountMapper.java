package com.gsm.platfra.api.entity.account.mapper;

import com.gsm.platfra.api.entity.account.TAccount;
import com.gsm.platfra.api.entity.account.dto.TAccountDto;
import com.gsm.platfra.api.entity.account.dto.TAccountJoinResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE) // unmappedTargetPolicy = ReportingPolicy.IGNORE
public interface TAccountMapper {
    TAccount tAccountPostToTAccount(TAccountDto.Post requestBody);
    TAccount tAccountPatchToTAccount(TAccountDto.Patch requestBody);
    TAccount tAccountProfileImageToTAccount(TAccountDto.ProfileImage requestBody);
    TAccountJoinResponseDto tAccountToTAccountResponse(TAccount taccount);
    List<TAccountJoinResponseDto> tAccountsToTAccountResponses(List<TAccount> taccounts);
}
