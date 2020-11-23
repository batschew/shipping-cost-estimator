function loadPredefinedPackages() {
    let carrierSelect = document.getElementById("inputGroupCarrierSelect");
    let predefinedPackageSelect = document.getElementById("inputGroupPredefinedPackageSelect")
    switch (carrierSelect.value){
        case "FedEx":
            predefinedPackageSelect.outerHTML=
                ("<select class=\"custom-select\" id=\"inputGroupPredefinedPackageSelect\">\n" +
                    "            <option value=\"ground\">Ground</option>\n" +
                    "            <option value=\"2day\">2 Day</option>\n" +
                    "            <option value=\"expressSaver\">Express Saver</option>\n" +
                    "            <option value=\"standardOvernight\">Standard Overnight</option>\n" +
                    "        </select>")
            break;
        case "DHL express":
            predefinedPackageSelect.outerHTML=
                ("<select class=\"custom-select\" id=\"inputGroupPredefinedPackageSelect\">\n" +
                    "            <option value=\"economyselect\">Economy Select</option>\n" +
                    "            <option value=\"expressenvelope\">Express Envelope</option>\n" +
                    "            <option value=\"sameday\">Same Day</option>\n" +
                    "            <option value=\"jumbobox\">Jumbo Box/option>\n" +
                    "        </select>")
            break;
        case "UPS":
            predefinedPackageSelect.outerHTML=
                ("<select class=\"custom-select\" id=\"inputGroupPredefinedPackageSelect\">\n" +
                    "            <option value=\"ground\">Ground</option>\n" +
                    "            <option value=\"express\">Express</option>\n" +
                    "            <option value=\"expedited\">Expedited</option>\n" +
                    "            <option value=\"nextdayair\">Next Day Air</option>\n" +
                    "        </select>")
            break;
        case "USPS":
            predefinedPackageSelect.outerHTML=
                ("<select class=\"custom-select\" id=\"inputGroupPredefinedPackageSelect\">\n" +
                    "            <option value=\"first\">First</option>\n" +
                    "            <option value=\"priority\">Priority</option>\n" +
                    "            <option value=\"express\">Express</option>\n" +
                    "        </select>")
            break;
    }
}