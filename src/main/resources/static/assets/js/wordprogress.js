new Chart(document.getElementById("pie-chart"), {
    type: 'pie',
    data: {
        labels: ["암기완료", "암기미완료"],
        datasets: [{
            backgroundColor: ["#41fc4a","#000000"],
            data: [$("#memo-count").val(),$("#total-count").val()]
        }]
    }
});