package za.co.wethinkcode.courier.dispatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DispatchCentre {

    private final String centreName;
    private final List<Parcel> parcels;

    public DispatchCentre(String centreName) {
        this.centreName = centreName;
        this.parcels = new ArrayList<>();
    }

    public String getCentreName() {
        return centreName;
    }

    public void addParcel(Parcel parcel) {
        if (findParcel(parcel.getTrackingNumber()) != null) {
            throw new IllegalArgumentException("Parcel with tracking number " + parcel.getTrackingNumber() + " already exists");
        }
        parcels.add(parcel);
    }

    public Parcel findParcel(String trackingNumber) {
        for (Parcel parcel : parcels) {
            if (parcel.getTrackingNumber().equals(trackingNumber)) {
                return parcel;
            }
        }
        return null;
    }

    public List<Parcel> getParcels() {
        return Collections.unmodifiableList(parcels);
    }

    public List<Insurable> insurableParcels() {
        List<Insurable> insurable = new ArrayList<>();
        for (Parcel parcel : parcels) {
            if (parcel instanceof Insurable) {
                insurable.add((Insurable) parcel);
            }
        }
        return insurable;
    }

    public double totalDeliveryRevenue() {
        double total = 0.0;
        for (Parcel parcel : parcels) {
            total += parcel.deliveryFee();
        }
        return total;
    }

    public double totalInsurancePremiums() {
        double total = 0.0;
        for (Insurable parcel : insurableParcels()) {
            total += parcel.insurancePremium();
        }
        return total;
    }
}
