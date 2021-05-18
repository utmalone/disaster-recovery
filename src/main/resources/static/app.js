let dropdown = document.querySelector("#job-entry").children;
let mainData;
let id = 3;

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

let click = document.getElementById("add-job-btn");
click.addEventListener("click", (event) => {
    createNewJobEntry();
});

const createNewJobEntry = function() {
    id++;
    let mainDiv = document.createElement("div");
    let htmlContent = "Job Code<select class=\"form-control custom-input-width\" type=\"text\"><option value=\"\">Job Code</option></select>Hours Worked<input class=\"form-control custom-input-width\" id=\"jc-" + id + "\">Total Compensation<input class=\"form-control custom-input-width\" type=\"text\" readonly id=\"jc-t-" + id + "\">";
    mainDiv.innerHTML = htmlContent;
    document.getElementById("job-entry").appendChild(mainDiv);
    dropdown = document.querySelector("#job-entry").children;
    getSomeData();
    bindEvents();
};

let submitButton = document.getElementById("submit-btn");
submitButton.addEventListener("click", (event) => {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/add_timesheet", true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    let jobSiteCode = document.querySelector("#job-code").value;
    let contractorName = document.querySelector("#contractor-name").value;
    let ddm = document.querySelector("#date").value.split("-");
    let workDate = new Date(ddm[2], Number(ddm[0]) - 1, ddm[1]).toISOString();
    let totalHoursWorked = calculateHoursWorked(dropdown);
    let totalEarned = calculateTotalPay(dropdown);
    console.log(totalHoursWorked, totalEarned);
    console.log(workDate);

    let data = JSON.stringify(
        {
            "jobSiteCode": jobSiteCode,
            "contractorName": contractorName,
            "date": workDate,
            "hoursWorked": totalHoursWorked,
            "totalEarned": totalEarned,
            "hoursRented": 20,
            "totalRent": 400,
            "status": false
        }
    );

    xhr.send(data);

});

const getSomeData = () => {
    fetch("http://localhost:8080/api/jobs")
        .then(response => response.json())
        .then(json => {
            let data = json;
            mainData = data;

            for (let i = 0; i < dropdown.length; i++) {

                //dropdown[i].children[0].innerHTML = "<option value=\"\">Job
                // Code</option>";

                if (dropdown[i].children[0].children.length === 1) {
                    for (let j = 0; j < data.length; j++) {
                        let child = document.createElement("option");
                        child.innerHTML = "<option value=\'" + data[j].jobCode + "\'>" +
                            data[j].jobCode + "</option>";
                        dropdown[i].children[0].appendChild(child);
                    }
                }

                // for (let j = 0; j < data.length; j++) {
                //     let child = document.createElement("option");
                //     child.innerHTML = "<option value=\'" + data[j].jobCode + "\'>" +
                //         data[j].jobCode + "</option>";
                //     dropdown[i].children[0].appendChild(child);
                // }
            }
        })
        .catch(error => console.log(error));
};

getSomeData();
bindEvents();