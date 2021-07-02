package ir.maktab.mappers.customer;

import ir.maktab.data.domain.Customer;
import ir.maktab.dtos.CustomerDto;
import ir.maktab.mappers.token.ConfirmationTokenMapper;
import ir.maktab.mappers.wallet.WalletMapper;
import ir.maktab.mappers.order.OrderMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class CustomerMapperImpl implements CustomerMapper {
    private final OrderMapper orderMapper;
    private final WalletMapper walletMapper;
    private final ConfirmationTokenMapper confirmationTokenMapper;

    @Lazy
    public CustomerMapperImpl(OrderMapper orderMapper, WalletMapper walletMapper, ConfirmationTokenMapper confirmationTokenMapper) {
        this.orderMapper = orderMapper;
        this.walletMapper = walletMapper;
        this.confirmationTokenMapper = confirmationTokenMapper;
    }

    @Override
    public Customer toCustomer(CustomerDto customerDto) {
        if (customerDto != null) {
            return (Customer) new Customer()
                    .setStatus(customerDto.getStatus())
                    .setWallet(walletMapper.toWallet(customerDto.getWalletDto()))
                    .setConfirmationToken(confirmationTokenMapper.toConfirmationToken(customerDto.getConfirmationTokenDto()))
                    .setId(customerDto.getId())
                    .setName(customerDto.getName())
                    .setFamily(customerDto.getFamily())
                    .setEmail(customerDto.getEmail())
                    .setPassword(customerDto.getPassword())
                    .setRole(customerDto.getRole());
        }
        return null;
    }

    @Override
    public CustomerDto toCustomerDto(Customer customer) {
        if (customer != null) {
            return (CustomerDto) new CustomerDto()
                    .setStatus(customer.getStatus())
                    .setWalletDto(walletMapper.toWalletDto(customer.getWallet()))
                    .setConfirmationTokenDto(confirmationTokenMapper.toConfirmationTokenDto(customer.getConfirmationToken()))
                    .setId(customer.getId())
                    .setName(customer.getName())
                    .setFamily(customer.getFamily())
                    .setEmail(customer.getEmail())
                    .setPassword(customer.getPassword())
                    .setRole(customer.getRole());
        }
        return null;
    }
}
