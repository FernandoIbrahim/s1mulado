<script>
import { getUserTestResultHistory } from '@/services/userService'

import * as d3 from "d3";

export default {
  name: "LineChart",
  data(){
    return {
        chartData: null
    }
  },
  methods: {

    async getChartData(){
        const response = await getUserTestResultHistory({ page: 0, size: 100, sort: null });
        const results = response.data.content;
        console.log(results)
        this.convertResult(results);
    },

    convertResult(results) {

        this.chartData = results
            .map(item => ({
                date: new Date(item.date), 
                value: item.correctAnswers
            }))
            .sort((a, b) => a.date - b.date);

        console.log(this.chartData);

    },

    drawChart() {


        const width = 500, height = 250, margin = 50;

        const svg = d3.select("#chart")
          .append("svg")
          .attr("width", width + margin * 2)
          .attr("height", height + margin * 2)
          .append("g")
          .attr("transform", `translate(${margin}, ${margin})`);

        const xScale = d3.scaleTime()
          .domain(d3.extent(this.chartData, d => d.date))
          .range([0, width]);

        const yScale = d3.scaleLinear()
          .domain([0, d3.max(this.chartData, d => d.value)])
          .range([height, 0]);

        const xAxis = d3.axisBottom(xScale).tickFormat(d3.timeFormat("%b %d"));
        const yAxis = d3.axisLeft(yScale);

        svg.append("g")
          .attr("transform", `translate(0, ${height})`)
          .call(xAxis);

        svg.append("g").call(yAxis);

        const line = d3.line()
          .x(d => xScale(d.date))
          .y(d => yScale(d.value))
          .curve(d3.curveMonotoneX);

        svg.append("path")
          .datum(this.chartData)
          .attr("fill", "none")
          .attr("stroke", "black")
          .attr("stroke-width", 2)
          .attr("d", line);

        svg.selectAll("circle")
          .data(this.chartData)
          .enter()
          .append("circle")
          .attr("cx", d => xScale(d.date))
          .attr("cy", d => yScale(d.value))
          .attr("r", 5)
          .attr("fill", "white")
          .attr("stroke", "black")
          .attr("stroke-width", 2);
        }   

    },
    async mounted() {
        await this.getChartData();
       this.drawChart();
    },

};
</script>

<template>
  <div id="chart"></div>
</template>