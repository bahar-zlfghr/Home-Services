package ir.maktab.service.manager;

import ir.maktab.data.domain.Manager;
import ir.maktab.data.repository.manager.ManagerRepository;
import ir.maktab.dtos.ManagerDto;
import ir.maktab.exceptions.NotFoundUserException;
import ir.maktab.mappers.manager.ManagerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    public ManagerServiceImpl(ManagerRepository managerRepository, ManagerMapper managerMapper) {
        this.managerRepository = managerRepository;
        this.managerMapper = managerMapper;
    }

    @Override
    public void saveManager(ManagerDto managerDto) {
        managerRepository.save(managerMapper.toManager(managerDto));
    }

    @Override
    public void updateManagerName(Integer id, String name) {
        managerRepository.updateManagerName(id, name);
    }

    @Override
    public void updateManagerFamily(Integer id, String family) {
        managerRepository.updateManagerFamily(id, family);
    }

    @Override
    public void updateManagerEmail(Integer id, String email) {
        managerRepository.updateManagerEmail(id, email);
    }

    @Override
    public void updateManagerPassword(String email, String previousPassword, String newPassword) {
        managerRepository.updateManagerPassword(email, previousPassword, newPassword);
    }

    @Override
    public void deleteManager(ManagerDto managerDto) {
        managerRepository.delete(managerMapper.toManager(managerDto));
    }

    @Override
    public ManagerDto getManagerByEmailAndPassword(String email, String password) throws NotFoundUserException {
        Optional<Manager> managerByEmailAndPassword = managerRepository.getManagerByEmailAndPassword(email, password);
        if (managerByEmailAndPassword.isPresent()) {
            return managerMapper.toManagerDto(managerByEmailAndPassword.get());
        }
        throw new NotFoundUserException("user.not.login");
    }
}
