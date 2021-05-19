let dropdown = document.querySelector("#job-entry").children;
let mainData;

let macDropdown = document.querySelector("#machine-entry").children;
let macData;

let id = 3;
let macId = 3;

const bindEvents = () => {
    for (let i = 0; i < dropdown.length; i++) {
        dropdown[i].children[0].addEventListener("change", (event) => {
            let currentPrice = findPrice(event.target.value, mainData);
            let totalPrice = 0;
            let idName = "jc-" + (i + 1);
            let hourInput = document.getElementById(idName);
            let tIdName = "jc-t-" + (i + 1);
            let totalInput = document.getElementById(tIdName);

            totalPrice = currentPrice * Number(hourInput.value);
            totalInput.value = totalPrice;
        });
    }

    for (let i = 0; i < dropdown.length; i++) {
        dropdown[i].children[1].addEventListener("input", (event) => {
            let currentPrice = findPrice(dropdown[i].children[0].value, mainData);
            let totalPrice = 0;
            let tIdName = "jc-t-" + (i + 1);
            let totalInput = document.getElementById(tIdName);

            totalPrice = currentPrice * Number(event.target.value);
            totalInput.value = totalPrice;
        });
    }
};

const bindMacEvents = () => {
    for (let i = 0; i < macDropdown.length; i++) {
        macDropdown[i].children[0].addEventListener("change", (event) => {
            let currentPrice = findMacPrice(event.target.value, macData);
            let totalPrice = 0;
            let idName = "mc-" + (i + 1);
            let hourInput = document.getElementById(idName);
            let tIdName = "mc-t-" + (i + 1);
            let totalInput = document.getElementById(tIdName);

            totalPrice = currentPrice * Number(hourInput.value);
            totalInput.value = totalPrice;
        });
    }

    for (let i = 0; i < dropdown.length; i++) {
        macDropdown[i].children[1].addEventListener("input", (event) => {
            let currentPrice = findMacPrice(macDropdown[i].children[0].value, macData);
            let totalPrice = 0;
            let tIdName = "mc-t-" + (i + 1);
            let totalInput = document.getElementById(tIdName);

            totalPrice = currentPrice * Number(event.target.value);
            totalInput.value = totalPrice;
        });
    }
};

const findPrice = (jobCode, data) => {
    let price = 0;

    for (let i = 0; i < data.length; i++) {
        if (data[i].jobCode === jobCode) {
            price = data[i].hourlyRate;
            break;
        }
    }

    return price;
};

const findMacPrice = (macCode, data) => {
    let price = 0;

    for (let i = 0; i < data.length; i++) {
        if (data[i].machineCode === macCode) {
            price = data[i].hourlyRent;
            break;
        }
    }

    return price;
};

const calculateHoursWorked = (dropdown) => {
    let totalHours = 0;

    for (let i = 0; i < dropdown.length; i++) {
        totalHours += Number(dropdown[i].children[1].value);
    }

    return totalHours;
};

const calculateTotalPay = (dropdown) => {
    let totalPay = 0;

    for (let i = 0; i < dropdown.length; i++) {
        totalPay += Number(dropdown[i].children[2].value);
    }

    return totalPay;
};

let addJobEntryBtn = document.getElementById("add-job-btn");
addJobEntryBtn.addEventListener("click", (event) => {
    createNewJobEntry();
});

let addMachineEntryBtn = document.getElementById("add-machine-btn");
addMachineEntryBtn.addEventListener("click", (event) => {
    createNewMachineEntry();
});

const createNewJobEntry = function() {
    id++;

    let mainDiv = document.createElement("div");
    let htmlContent = "Job Code<select class=\"form-control custom-input-width\" type=\"text\"><option value=\"\">Job Code</option></select>Hours Worked<input class=\"form-control custom-input-width\" id=\"jc-" + id + "\">Total Compensation<input class=\"form-control custom-input-width\" type=\"text\" readonly id=\"jc-t-" + id + "\">";
    mainDiv.innerHTML = htmlContent;
    document.getElementById("job-entry").appendChild(mainDiv);
    dropdown = document.querySelector("#job-entry").children;
    getJobData();
    bindEvents();
};

const createNewMachineEntry = () => {
    macId++;

    let mainDiv = document.createElement("div");
    let htmlContent = "Machine Code<select class=\"form-control" +
        " custom-input-width\" type=\"text\"><option value=\"\">Machine" +
        " Code</option></select>Hours Used<input class=\"form-control" +
        " custom-input-width\" id=\"mc-" + macId + "\">Total Rent" +
        " Price<input" +
        " class=\"form-control custom-input-width\" type=\"text\" readonly" +
        " id=\"mc-t-" + macId + "\">";

    mainDiv.innerHTML = htmlContent;
    document.getElementById("machine-entry").appendChild(mainDiv);
    macDropdown = document.querySelector("#machine-entry").children;
    getMacData();
    bindMacEvents();
};

let submitButton = document.getElementById("submit-btn");
submitButton.addEventListener("click", (event) => {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://ec2-52-87-175-125.compute-1.amazonaws.com:8089/add_timesheet", true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    let jobSiteCode = document.querySelector("#job-code").value;
    let contractorName = document.querySelector("#contractor-name").value;
    let ddm = document.querySelector("#date").value.split("-");
    let workDate = new Date(ddm[2], Number(ddm[0]) - 1, ddm[1]).toISOString();
    let totalHoursWorked = calculateHoursWorked(dropdown);
    let totalEarned = calculateTotalPay(dropdown);
    let totalHoursUsed = calculateHoursWorked(macDropdown);
    let totalRentPrice = calculateTotalPay(macDropdown);
    // console.log(totalHoursWorked, totalEarned);
    // console.log(workDate);

    let data = JSON.stringify(
        {
            "jobSiteCode": jobSiteCode,
            "contractorName": contractorName,
            "date": workDate,
            "hoursWorked": totalHoursWorked,
            "totalEarned": totalEarned,
            "hoursRented": totalHoursUsed,
            "totalRent": totalRentPrice,
            "status": false
        }
    );

    xhr.send(data);

    submitButton.disabled = true;
    submitButton.innerText = "Submitted!";

    let url = window.location.href;
    url = url.split("add_timesheet");
    let timeCardSubmissionURL = url[0] + "time_card_submission";
    console.log(timeCardSubmissionURL);

    let goBackButton = document.getElementById("go-back-btn");
    goBackButton.setAttribute("href", timeCardSubmissionURL);

    document.getElementById("go-back-btn").style.display = "inline";

    // console.log(calculateHoursWorked(macDropdown));
    // console.log(calculateTotalPay(macDropdown));
});

const getJobData = () => {
    fetch("http://localhost:8080/api/jobs")
        .then(response => response.json())
        .then(json => {
            let data = json;
            mainData = data;

            for (let i = 0; i < dropdown.length; i++) {

                if (dropdown[i].children[0].children.length === 1) {
                    for (let j = 0; j < data.length; j++) {
                        let child = document.createElement("option");
                        child.setAttribute("value", data[j].jobCode);
                        child.innerHTML = data[j].jobCode;
                        dropdown[i].children[0].appendChild(child);
                    }
                }
            }
        })
        .catch(error => console.log(error));
};

const getMacData = () => {
    fetch("http://localhost:8080/api/machines")
        .then(response => response.json())
        .then(json => {
            let data = json;
            macData = data;

            for (let i = 0; i < macDropdown.length; i++) {

                if (macDropdown[i].children[0].children.length === 1) {
                    for (let j = 0; j < data.length; j++) {
                        let child = document.createElement("option");
                        child.setAttribute("value", data[j].machineCode)
                        child.innerHTML = data[j].machineCode;
                        macDropdown[i].children[0].appendChild(child);
                    }
                }
            }
        })
        .catch(error => console.log(error));
};

getJobData();
bindEvents();

getMacData();
bindMacEvents();