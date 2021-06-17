package ir.maktab.dtos;

import ir.maktab.data.enums.OfferStatus;

import java.util.Date;

/**
 * @author : Bahar Zolfaghari
 **/
public class OfferDto {
    private Integer id;
    private Date submitDate;
    private Long suggestedPrice;
    private Integer durationDoWork;
    private Date startTime;
    private OrderDto orderDto;
    private SpecialistDto specialistDto;
    private OfferStatus status;

    public Integer getId() {
        return id;
    }

    public OfferDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public OfferDto setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
        return this;
    }

    public Long getSuggestedPrice() {
        return suggestedPrice;
    }

    public OfferDto setSuggestedPrice(Long suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
        return this;
    }

    public Integer getDurationDoWork() {
        return durationDoWork;
    }

    public OfferDto setDurationDoWork(Integer durationDoWork) {
        this.durationDoWork = durationDoWork;
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public OfferDto setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public OfferDto setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
        return this;
    }

    public SpecialistDto getSpecialistDto() {
        return specialistDto;
    }

    public OfferDto setSpecialistDto(SpecialistDto specialistDto) {
        this.specialistDto = specialistDto;
        return this;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public OfferDto setStatus(OfferStatus status) {
        this.status = status;
        return this;
    }
}
