/**
 * Copyright (c) 2015, ProductLayer GmbH All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * - Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer. 
 * 
 * - Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.productlayer.core.beans;

import java.util.ArrayList;
import java.util.List;

import com.productlayer.core.utils.StringUtils;

public enum CategoryEnum {
    Magazines("pl-prod-cat-magazines"),

    Books("pl-prod-cat-books"), Books_ChildrenAndTeenagers("pl-prod-cat-books-children_and_teenagers"), Books_ComicsAndPicturestories(
            "pl-prod-cat-books-comics_and_picturestories"), Books_HealthMindAndBody(
            "pl-prod-cat-books-health_mind_and_body"), Books_ComputerAndInternet(
            "pl-prod-cat-books-computer_and_internet"), Books_MysterySuspense(
            "pl-prod-cat-books-mystery_suspense"), Books_Romance("pl-prod-cat-books-romance"), Books_RelegionSpirituality(
            "pl-prod-cat-books-religious_spirituality"), Books_NovelStories("pl-prod-cat-books-novel_stories"), Books_SciencefictionAndFantasy(
            "pl-prod-cat-books-sciencefiction_and_fantasy"), Books_EconomyInvestments(
            "pl-prod-cat-books-economy_investments"),

    Electronic("pl-prod-cat-electronic"), Electronic_TV("pl-prod-cat-electronic-tv"), Electronic_Camera(
            "pl-prod-cat-electronic-camera"), Electronic_Computer("pl-prod-cat-electronic-computer"), Electronic_Computer_Notebook(
            "pl-prod-cat-electronic-computer-notebook"), Electronic_Computer_Tablet(
            "pl-prod-cat-electronic-computer-tablet"), Electronic_Computer_Systems(
            "pl-prod-cat-electronic-computer-systems"), Electronic_Computer_Components(
            "pl-prod-cat-electronic-computer-components"), Electronic_Computer_Audio(
            "pl-prod-cat-electronic-computer-audio"), Electronic_Computer_Video(
            "pl-prod-cat-electronic-computer-video"), Electronic_Computer_Mainboard(
            "pl-prod-cat-electronic-computer-mainboard"), Electronic_Computer_Monitor(
            "pl-prod-cat-electronic-computer-monitor"), Electronic_Computer_Network(
            "pl-prod-cat-electronic-computer-network"), Electronic_Computer_CPU(
            "pl-prod-cat-electronic-computer-cpu"), Electronic_Computer_RAM(
            "pl-prod-cat-electronic-computer-ram"), Electronic_Computer_Storage(
            "pl-prod-cat-electronic-computer-storage"), Electronic_Computer_GraphicCard(
            "pl-prod-cat-electronic-computer-graphic_card"), Electronic_Computer_InputDevice(
            "pl-prod-cat-electronic-computer-input_device"), Electronic_Computer_Cable(
            "pl-prod-cat-electronic-computer-cable"), Electronic_Phones("pl-prod-cat-electronic-phones"), Electronic_Wearables(
            "pl-prod-cat-electronic-wearables"), Electronic_PrinterAndScanner(
            "pl-prod-cat-electronic-printer_and_scanner"), Electronic_PrinterAndScanner_3DPrinter(
            "pl-prod-cat-electronic-printer_and_scanner-3dprinter"), Electronic_PrinterAndScanner_Scanner(
            "pl-prod-cat-electronic-printer_and_scanner-scanner"), Electronic_PrinterAndScanner_Printer(
            "pl-prod-cat-electronic-printer_and_scanner-printer"), Electronic_PrinterAndScanner_BarcodePrinter(
            "pl-prod-cat-electronic-printer_and_scanner-barcode_printer"), Electronic_PrinterAndScanner_BarcodeScanner(
            "pl-prod-cat-electronic-printer_and_scanner-barcode_scanner"), Electronic_PrinterAndScanner_ScannerAccessories(
            "pl-prod-cat-electronic-printer_and_scanner-scanner_accessories"), Electronic_PrinterAndScanner_PrinterAccessories(
            "pl-prod-cat-electronic-printer_and_scanner-printer_accessories"), Electronic_Audio(
            "pl-prod-cat-electronic-audio"), Electronic_Photography("pl-prod-cat-electronic-photography"), Electronic_Visual(
            "pl-prod-cat-electronic-visual"),

    Food("pl-prod-cat-food"), Food_Dairy("pl-prod-cat-food-dairy"), // Milchprodukte
    Food_Fruit("pl-prod-cat-food-fruit"), // Fruechte
    Food_Grains("pl-prod-cat-food-grains"), // Koerner
    Food_Meat("pl-prod-cat-food-meat"), // Fleisch
    Food_Seafood("pl-prod-cat-food-seafood"), // Meeresfrüchte
    Food_Sweets("pl-prod-cat-food-sweets"), // Sueßigkeiten
    Food_Vegetables("pl-prod-cat-food-vegetables"), // Gemuese
    Food_Bread("pl-prod-cat-food-bread"), // Brot
    Food_InstantMeal("pl-prod-cat-food-instant_meal"), // Fertiggericht
    Food_Sauce("pl-prod-cat-food-sauce"), // Soßen
    Food_Trimmings("pl-prod-cat-food-trimmings"), // Beilagen
    Food_Herbs("pl-prod-cat-food-herbs"), // Kräuter
    Food_Snacks("pl-prod-cat-food-snacks"),

    Pet("pl-prod-cat-pet"), Pet_Food("pl-prod-cat-pet-food"), Pet_Care("pl-prod-cat-pet-care"), Pet_Toys(
            "pl-prod-cat-pet-toys"),

    Ingredients("pl-prod-cat-food-ingredients"), // Zutaten
    Ingredients_Spice("pl-prod-cat-food-ingredients-spice"), // Gewuerze
    Ingredients_Minerals("pl-prod-cat-food-ingredients-minerals"), // Mineralstoffe

    Drinks("pl-prod-cat-drinks"), Drinks_Alcoholic("pl-prod-cat-drinks-alcoholic"), Drinks_Non_Alcoholic(
            "pl-prod-cat-drinks-non_alcoholic"),

    Clothes("pl-prod-cat-clothes"), Clothes_Jeans("pl-prod-cat-clothes-jeans"), Clothes_Pants(
            "pl-prod-cat-clothes-pants"), Clothes_Suites("pl-prod-cat-clothes-suites"), Clothes_Shirts(
            "pl-prod-cat-clothes-shirts"), Clothes_Underwear("pl-prod-cat-clothes-underwear"), Clothes_Footwear(
            "pl-prod-cat-clothes-footwear"),

    IndustriaAndScientific("pl-prod-cat-industrial_and_scientific"),

    HomeAndKitchen("pl-prod-cat-home_and_kitchen"), HomeAndKitchen_Cleaning(
            "pl-prod-cat-home_and_kitchen-cleaning"),

    HealthAndPersonalCare("pl-prod-cat-health_and_personal_care"), HealthAndPersonalCare_Beauty(
            "pl-prod-cat-health_and_personal_care-beauty"),

    BabyCare("pl-prod-cat-baby_care"),

    Movies("pl-prod-cat-movies"), Movies_BluRay("pl-prod-cat-movies-bluray"), Movies_DVD(
            "pl-prod-cat-movies-dvd"),

    Music("pl-prod-cat-music"), Music_DCC("pl-prod-cat-music-dcc"), Music_DVDr("pl-prod-cat-music-dvd_r"), Music_MVD(
            "pl-prod-cat-music-mvd"), Music_8TrackCartridge("pl-prod-cat-music-8_track_cartridge"), Music_HDDVD(
            "pl-prod-cat-music-hd_dvd"), Music_CDV("pl-prod-cat-music-cdv"), Music_BluRayR(
            "pl-prod-cat-music-bluray_r"), Music_Cylinder("pl-prod-cat-music-cylinder"), Music_Minidisc(
            "pl-prod-cat-music-minidisc"), Music_Lathe_Cut("pl-prod-cat-music-lathe_cut"), Music_FloppyDisk(
            "pl-prod-cat-music-floppy_disk"), Music_ReelToReel("pl-prod-cat-music-reel_to_reel"), Music_BoxSet(
            "pl-prod-cat-music-box_set"), Music_VHS("pl-prod-cat-music-VHS"), Music_DAT(
            "pl-prod-cat-music-DAT"), Music_4TrackCartridge("pl-prod-cat-music-4_track_cartridge"), Music_UMD(
            "pl-prod-cat-music-UMD"), Music_AllMedia("pl-prod-cat-music-all_media"), Music_Betamax(
            "pl-prod-cat-music-betamax"), Music_File("pl-prod-cat-music-file"), Music_CDr(
            "pl-prod-cat-music-cd_r"), Music_Compilation("pl-prod-cat-music-compilation"), Music_Hybrid(
            "pl-prod-cat-music-hybrid"), Music_Microcassette("pl-prod-cat-music-microcassette"), Music_DVD(
            "pl-prod-cat-music-dvd"), Music_Shellac("pl-prod-cat-music-shellac"), Music_Cassette(
            "pl-prod-cat-music-cassette"), Music_MemoryStick("pl-prod-cat-music-memory_stick"), Music_BluRay(
            "pl-prod-cat-music-blu_ray"), Music_Acetate("pl-prod-cat-music-acetate"), Music_CD(
            "pl-prod-cat-music-cd"), Music_SACD("pl-prod-cat-music-sacd"), Music_FlexiDisc(
            "pl-prod-cat-music-flexi_disc"), Music_Vinyl("pl-prod-cat-music-vinyl"), Music_SelectaVision(
            "pl-prod-cat-music-selecta_vision"), Music_Laserdisc("pl-prod-cat-music-laserdisc"),

    Office("pl-prod-cat-office"), Office_Supplies("pl-prod-cat-office-supplies"),

    Furniture("pl-prod-cat-furniture"), Furniture_Chairs("pl-prod-cat-furniture-chairs"), Furniture_Tables(
            "pl-prod-cat-furniture-tables"), Furniture_Shelfes("pl-prod-cat-furniture-shelfes"),

    SafetySecuritySurveillance("pl-prod-cat-safety_security_Surveillance"),

    StorageAndHaulageContainers("pl-prod-cat-storage_and_haulage_containers"),

    Tools("pl-prod-cat-tools"), Tools_Garden("pl-prod-cat-tools-garden"), Tools_Electronic(
            "pl-prod-cat-tools-electronic"), Tools_Storage("pl-prod-cat-tools-storage"), Tools_ArtsCraftsAndNeedlework(
            "pl-prod-cat-tools-art_craft_and_needlework"),

    BuildingProducts("pl-prod-cat-building_products"),

    Toys("pl-prod-cat-toys"),

    Automotive("pl-prod-cat-automotive"),

    PlumbingHeatingVentilationAndAirConditioning(
            "pl-prod-cat-plumbing_heating_ventilation_and_air_conditioning"),

    PersonalAccessories("pl-prod-cat-personal_accessories"),

    LiveAnimals("pl-prod-cat-live_animals"),

    Fuels("pl-prod-cat-fuels"),

    Lubricants("pl-prod-cat-lubricants"),

    SportEquipment("pl-prod-cat-sport_equipment"),

    Others("pl-prod-cat-others");

    private CategoryEnum(final String text) {
        this.text = text;
    }

    private final String text;

    public static CategoryEnum fromString(String text) {
        if (text != null) {
            for (CategoryEnum cat : CategoryEnum.values()) {
                if (text.equalsIgnoreCase(cat.text)) {
                    return cat;
                }
            }
        }
        return null;
    }

    public static List<String> getOptions() {
        List<String> options = new ArrayList<String>();

        for (CategoryEnum cat : CategoryEnum.values()) {
            options.add(cat.text);
        }

        return options;
    }

    public static CategoryEnum fromGPC_S_CD(String classification) {
        if (classification == null) {
            return CategoryEnum.Others;
        }

        if (classification.equals("10000000")) {
            // Pet Care/Food
            return CategoryEnum.Pet;
        } else if (classification.equals("47000000")) {
            // Cleaning/Hygiene Products
            return CategoryEnum.HomeAndKitchen_Cleaning;
        } else if (classification.equals("50000000")) {
            // Food/Beverage/Tobacco
            return CategoryEnum.Food;
        } else if (classification.equals("51000000")) {
            // Healthcare
            return CategoryEnum.HealthAndPersonalCare;
        } else if (classification.equals("53000000")) {
            // Beauty/Personal Care/Hygiene
            return CategoryEnum.HealthAndPersonalCare;
        } else if (classification.equals("54000000")) {
            // Baby Care
            return CategoryEnum.BabyCare;
        } else if (classification.equals("58000000")) {
            // Cross Segment
            return CategoryEnum.Others;
        } else if (classification.equals("60000000")) {
            // Textual/Printed/Reference Materials
            return CategoryEnum.Books;
        } else if (classification.equals("61000000")) {
            // Music
            return CategoryEnum.Music;
        } else if (classification.equals("62000000")) {
            // Stationery/Office Machinery/Occasion Supplies
            // Schreibwaren
            return CategoryEnum.Office_Supplies;
        } else if (classification.equals("63000000")) {
            // Footwear
            return CategoryEnum.Clothes_Footwear;
        } else if (classification.equals("64000000")) {
            // Personal Accessories/Jewellery
            return CategoryEnum.PersonalAccessories;
        } else if (classification.equals("65000000")) {
            // Computing
            return CategoryEnum.Electronic_Computer;
        } else if (classification.equals("66000000")) {
            // Communications
            return CategoryEnum.Electronic_Phones;
        } else if (classification.equals("67000000")) {
            // Clothing
            return CategoryEnum.Clothes;
        } else if (classification.equals("68000000")) {
            // Audio Visual/Photography
            return CategoryEnum.Electronic;
        } else if (classification.equals("70000000")) {
            // Arts/Crafts/Needlework
            return CategoryEnum.Tools_ArtsCraftsAndNeedlework;
        } else if (classification.equals("71000000")) {
            // Sports Equipment
            return CategoryEnum.SportEquipment;
        } else if (classification.equals("72000000")) {
            // Home Appliances
            // Haushaltsgeräte
            return CategoryEnum.Electronic;
        } else if (classification.equals("73000000")) {
            // Kitchen Merchandise
            return CategoryEnum.HomeAndKitchen;
        } else if (classification.equals("75000000")) {
            // Household/Office Furniture/Furnishings
            // Schreibtische / Regeale / Sessel
            return CategoryEnum.Furniture;
        } else if (classification.equals("77000000")) {
            // Automotive
            return CategoryEnum.Automotive;
        } else if (classification.equals("78000000")) {
            // Electrical Supplies
            // Kabel
            return CategoryEnum.Tools;
        } else if (classification.equals("79000000")) {
            // Plumbing/Heating/Ventilation/Air Conditioning
            return CategoryEnum.PlumbingHeatingVentilationAndAirConditioning;
        } else if (classification.equals("80000000")) {
            // Tools/Equipment – Hand
            return CategoryEnum.Tools;
        } else if (classification.equals("81000000")) {
            // Lawn/Garden Supplies
            return CategoryEnum.Tools_Garden;
        } else if (classification.equals("82000000")) {
            // Tools/Equipment – Power
            return CategoryEnum.Tools_Electronic;
        } else if (classification.equals("83000000")) {
            // Building Products
            return CategoryEnum.BuildingProducts;
        } else if (classification.equals("84000000")) {
            // Tool Storage/Workshop Aids
            return CategoryEnum.Tools_Storage;
        } else if (classification.equals("85000000")) {
            // Safety/Protection – DIY
            return CategoryEnum.SafetySecuritySurveillance;
        } else if (classification.equals("86000000")) {
            // Toys/Games
            return CategoryEnum.Toys;
        } else if (classification.equals("87000000")) {
            // Fuels
            return CategoryEnum.Fuels;
        } else if (classification.equals("88000000")) {
            // Lubricants
            // Schmierstoffe
            return CategoryEnum.Lubricants;
        } else if (classification.equals("89000000")) {
            // Live Animals
            return CategoryEnum.LiveAnimals;
        } else if (classification.equals("91000000")) {
            // Safety/Security/Surveillance
            return CategoryEnum.SafetySecuritySurveillance;
        } else if (classification.equals("92000000")) {
            // Storage/Haulage Containers
            return CategoryEnum.StorageAndHaulageContainers;
        }

        return CategoryEnum.Others;
    }

    public static boolean isSubcategoryOf(String child, CategoryEnum parent) {
        if (StringUtils.hasText(child) && child.startsWith(parent.toString())) {
            return true;
        }

        return false;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
