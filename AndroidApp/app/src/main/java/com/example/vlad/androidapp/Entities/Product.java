package com.example.vlad.androidapp.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("is_dead")
    @Expose
    private Boolean isDead;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("is_discontinued")
    @Expose
    private Boolean isDiscontinued;
    @SerializedName("price_in_cents")
    @Expose
    private Integer priceInCents;
    @SerializedName("regular_price_in_cents")
    @Expose
    private Integer regularPriceInCents;
    @SerializedName("limited_time_offer_savings_in_cents")
    @Expose
    private Integer limitedTimeOfferSavingsInCents;
    @SerializedName("limited_time_offer_ends_on")
    @Expose
    private Object limitedTimeOfferEndsOn;
    @SerializedName("bonus_reward_miles")
    @Expose
    private Integer bonusRewardMiles;
    @SerializedName("bonus_reward_miles_ends_on")
    @Expose
    private Object bonusRewardMilesEndsOn;
    @SerializedName("stock_type")
    @Expose
    private String stockType;
    @SerializedName("primary_category")
    @Expose
    private String primaryCategory;
    @SerializedName("secondary_category")
    @Expose
    private String secondaryCategory;
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("package")
    @Expose
    private String _package;
    @SerializedName("package_unit_type")
    @Expose
    private String packageUnitType;
    @SerializedName("package_unit_volume_in_milliliters")
    @Expose
    private Integer packageUnitVolumeInMilliliters;
    @SerializedName("total_package_units")
    @Expose
    private Integer totalPackageUnits;
    @SerializedName("volume_in_milliliters")
    @Expose
    private Integer volumeInMilliliters;
    @SerializedName("alcohol_content")
    @Expose
    private Integer alcoholContent;
    @SerializedName("price_per_liter_of_alcohol_in_cents")
    @Expose
    private Integer pricePerLiterOfAlcoholInCents;
    @SerializedName("price_per_liter_in_cents")
    @Expose
    private Integer pricePerLiterInCents;
    @SerializedName("inventory_count")
    @Expose
    private Integer inventoryCount;
    @SerializedName("inventory_volume_in_milliliters")
    @Expose
    private Integer inventoryVolumeInMilliliters;
    @SerializedName("inventory_price_in_cents")
    @Expose
    private Integer inventoryPriceInCents;
    @SerializedName("sugar_content")
    @Expose
    private Object sugarContent;
    @SerializedName("producer_name")
    @Expose
    private String producerName;
    @SerializedName("released_on")
    @Expose
    private String releasedOn;
    @SerializedName("has_value_added_promotion")
    @Expose
    private Boolean hasValueAddedPromotion;
    @SerializedName("has_limited_time_offer")
    @Expose
    private Boolean hasLimitedTimeOffer;
    @SerializedName("has_bonus_reward_miles")
    @Expose
    private Boolean hasBonusRewardMiles;
    @SerializedName("is_seasonal")
    @Expose
    private Boolean isSeasonal;
    @SerializedName("is_vqa")
    @Expose
    private Boolean isVqa;
    @SerializedName("is_ocb")
    @Expose
    private Boolean isOcb;
    @SerializedName("is_kosher")
    @Expose
    private Boolean isKosher;
    @SerializedName("value_added_promotion_description")
    @Expose
    private Object valueAddedPromotionDescription;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("serving_suggestion")
    @Expose
    private String servingSuggestion;
    @SerializedName("tasting_note")
    @Expose
    private String tastingNote;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("image_thumb_url")
    @Expose
    private String imageThumbUrl;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("varietal")
    @Expose
    private String varietal;
    @SerializedName("style")
    @Expose
    private String style;
    @SerializedName("tertiary_category")
    @Expose
    private String tertiaryCategory;
    @SerializedName("sugar_in_grams_per_liter")
    @Expose
    private Object sugarInGramsPerLiter;
    @SerializedName("clearance_sale_savings_in_cents")
    @Expose
    private Integer clearanceSaleSavingsInCents;
    @SerializedName("has_clearance_sale")
    @Expose
    private Boolean hasClearanceSale;
    @SerializedName("product_no")
    @Expose
    private Integer productNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsDead() {
        return isDead;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public Boolean getIsDiscontinued() {
        return isDiscontinued;
    }

    public Integer getPriceInCents() {
        return priceInCents;
    }

    public Integer getRegularPriceInCents() {
        return regularPriceInCents;
    }

    public Integer getLimitedTimeOfferSavingsInCents() {
        return limitedTimeOfferSavingsInCents;
    }

    public Object getLimitedTimeOfferEndsOn() {
        return limitedTimeOfferEndsOn;
    }

    public Integer getBonusRewardMiles() {
        return bonusRewardMiles;
    }

    public Object getBonusRewardMilesEndsOn() {
        return bonusRewardMilesEndsOn;
    }

    public String getStockType() {
        return stockType;
    }

    public String getPrimaryCategory() {
        return primaryCategory;
    }

    public String getSecondaryCategory() {
        return secondaryCategory;
    }

    public String getOrigin() {
        return origin;
    }

    public String getPackage() {
        return _package;
    }

    public void setPackage(String _package) {
        this._package = _package;
    }

    public String getPackageUnitType() {
        return packageUnitType;
    }


    public Integer getPackageUnitVolumeInMilliliters() {
        return packageUnitVolumeInMilliliters;
    }

    public Integer getTotalPackageUnits() {
        return totalPackageUnits;
    }

    public Integer getVolumeInMilliliters() {
        return volumeInMilliliters;
    }

    public Integer getAlcoholContent() {
        return alcoholContent;
    }

    public Integer getPricePerLiterOfAlcoholInCents() {
        return pricePerLiterOfAlcoholInCents;
    }

    public Integer getPricePerLiterInCents() {
        return pricePerLiterInCents;
    }

    public Integer getInventoryCount() {
        return inventoryCount;
    }

    public Integer getInventoryVolumeInMilliliters() {
        return inventoryVolumeInMilliliters;
    }

    public Integer getInventoryPriceInCents() {
        return inventoryPriceInCents;
    }

    public Object getSugarContent() {
        return sugarContent;
    }

    public String getProducerName() {
        return producerName;
    }

    public String getReleasedOn() {
        return releasedOn;
    }

    public Boolean getHasValueAddedPromotion() {
        return hasValueAddedPromotion;
    }

    public Boolean getHasLimitedTimeOffer() {
        return hasLimitedTimeOffer;
    }

    public Boolean getHasBonusRewardMiles() {
        return hasBonusRewardMiles;
    }

    public Boolean getIsSeasonal() {
        return isSeasonal;
    }

    public Boolean getIsVqa() {
        return isVqa;
    }

    public Boolean getIsOcb() {
        return isOcb;
    }

    public Boolean getIsKosher() {
        return isKosher;
    }

    public Object getValueAddedPromotionDescription() {
        return valueAddedPromotionDescription;
    }

    public Object getDescription() {
        return description;
    }

    public String getServingSuggestion() {
        return servingSuggestion;
    }

    public String getTastingNote() {
        return tastingNote;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getImageThumbUrl() {
        return imageThumbUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getVarietal() {
        return varietal;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTertiaryCategory() {
        return tertiaryCategory;
    }

    public Object getSugarInGramsPerLiter() {
        return sugarInGramsPerLiter;
    }


    public Integer getClearanceSaleSavingsInCents() {
        return clearanceSaleSavingsInCents;
    }

    public Boolean getHasClearanceSale() {
        return hasClearanceSale;
    }

    public Integer getProductNo() {
        return productNo;
    }
}