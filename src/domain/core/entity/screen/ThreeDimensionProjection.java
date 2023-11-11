package domain.core.entity.screen;

import domain.core.entity.theatre.Theatre;

public class ThreeDimensionProjection extends Screen{
    public ThreeDimensionProjection(int id, SeatConfiguration seatConfiguration) {
        super(id, seatConfiguration);
    }

    @Override
    public String toString() {
        return "3D Screen Projection";
    }
}
