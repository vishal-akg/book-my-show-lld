package domain.core.entity.seat.state;

public interface SeatState {
    void lock();
    void reserve();
    void unlock();
}
