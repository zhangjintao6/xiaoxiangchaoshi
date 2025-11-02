<template>
    <div>
        <canvas id="pie-chart"></canvas>
    </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { Chart, registerables } from 'chart.js';
import 'chartjs-adapter-date-fns';

export default {
    setup() {
        const chartData = ref({
            labels: ['类别1', '类别2', '类别3', '类别4'],
            datasets: [
                {
                    data: [12, 19, 3, 5],
                    backgroundColor: ['#f87979', '#7f79f8', '#79f8a6', '#f8d279'],
                    borderColor: '#fff',
                    borderWidth: 1,
                },
            ],
        });

        onMounted(() => {
            const ctx = document.getElementById('pie-chart').getContext('2d');
            new Chart(ctx, {
                type: 'pie',
                data: chartData.value,
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                },
            });
        });

        return { chartData };
    },
};
</script>

<style scoped>
/* 添加任何需要的样式 */
</style>
