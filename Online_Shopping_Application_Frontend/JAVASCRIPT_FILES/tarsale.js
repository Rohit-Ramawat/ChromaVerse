const tarsaleChart = document.getElementById('tarsale').getContext('2d');
const tarsale = new Chart(tarsaleChart,{
    type:'line',
    data:{
        labels:['jan','feb','mar','apr','may','jun','jul','aug','sep','oct','nov','dec'],
        datasets:[{
            data:[1200,1990,387,598,276,376,654,764,2546,454,776,543],
        borderColor:[
            'rgb(59,197,154)'
        ],
        borderWidth:1
        },{
            data:[1100,1390,1387,798,376,776,254,464,1146,654,476,894],
        borderColor:[
            'rgb(153,102,255)'
        ],
        borderWidth:1
        }]
        
    },
    options:{
        respnsive:true,
        plugins:{
            legend:{
                display:false
            }
        }
    }
})