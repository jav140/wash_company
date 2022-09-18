package uz.jl.task2_2.services.auth;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uz.jl.task2_2.domains.sub.ActivationCode;
import uz.jl.task2_2.dtos.auth.AuthUserDTO;
import uz.jl.task2_2.exceptions.GenericNotFoundException;
import uz.jl.task2_2.repository.auth.ActivationCodeRepository;
import uz.jl.task2_2.utils.BaseUtils;

import java.time.LocalDateTime;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class ActivationCodeService {
    private final BaseUtils baseUtils;
    private final ActivationCodeRepository repository;

    @Value("${activation.link.expiry.in.minutes}")
    private long activationLinkValidTillInMinutes;

    public ActivationCode generateCode(@NonNull AuthUserDTO authUserDTO) {
        String codeForEncoding = "" + UUID.randomUUID() + System.currentTimeMillis();
        String encodedActivationCode = baseUtils.encode(codeForEncoding);
        ActivationCode activationCode = ActivationCode.builder()
                .activationLink(encodedActivationCode)
                .userId(authUserDTO.getId())
                .validTill(LocalDateTime.now().plusMinutes(activationLinkValidTillInMinutes))
                .build();
        return repository.save(activationCode);
    }

    public ActivationCode findByActivationLink(@NonNull String activationLink) {
        return repository.findByActivationLink(activationLink).orElseThrow(() ->
        {
            throw new GenericNotFoundException("Activation Link Not Found", 404);
        });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
