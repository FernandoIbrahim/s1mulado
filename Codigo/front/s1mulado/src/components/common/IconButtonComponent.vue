<template>
    <div class="tooltip">

        <button :class="{ 'on-main-url': isMainURL }"
            class="flex flex-col justify-center items-center bg-white prevent-selection cursor-default p-0.5 rounded-sm w-20"
            @click = "navigate" 
            @mouseover= "isHovering = true" @mouseout="isHovering = false"
            >
            <!-- <span class="material-symbols-outlined prevent-select"> {{ content }} </span> -->
            <svg class="w-7 h-7 justify-center items-center p-0.5" :fill="buttonIconStyle" >
                <use :href="`/icons/icons.svg#${content}`" />
            </svg>

            <p class="text-xs" :class="buttonTextColor">{{title}}</p>

        </button>


    </div>
</template>
  


<script>

export default {

    name: 'IconButtonComponent',
    props: {
        content: {
            type: String,
            required: true
        },
        mainUrl: {
            type: String,
            required: false
        },
        title: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            isHovering: false,
            currentPath: '',
            buttonIconColor: 'grey'
        };
    },
    computed: {
        isMainURL() {
            return this.mainUrl === this.currentPath;
        },
        buttonIconStyle() {
            return this.isHovering ? 'black' : '#6b7280';
        },
        buttonTextColor(){
            return this.isHovering ? 'text-black' : 'text-gray-500';
        }
    },
    methods: {
        navigate() {
            if (this.mainUrl.includes('http')) {
                window.open(`${this.mainUrl}`);
            }
            else {
                this.$router.push(`${this.mainUrl}`);
            }
        },
        updateCurrentPath() {
            this.currentPath = window.location.pathname;
        }
    },
    watch: {
        '$route'() {
            this.updateCurrentPath();
        }
    },
    created() {
        console.log('Objeto criado');
    },
    components: {
    }
};

//'button-icon-color-change': (isHovering && !isMainURL)

</script>


  
<style>
.tooltip {
  position: relative;
  display: inline-block;
}

.tooltiptext {
  visibility: hidden;
  background-color: #555;
  color: #fff;
  text-align: center;
  padding: 3px 8px;
  border-radius: 2px;
  white-space: nowrap;

  /* Position the tooltip text */
  position: absolute;
  z-index: 1;
  top: 120%;
  left: 50%;
  transform: translateX(-50%); /* Centraliza o tooltip horizontalmente */
  
  /* Fade in tooltip */
  opacity: 0;
  transition: opacity 0.3s;
}

.tooltiptext::after {
  content: "";
  position: absolute;
  top: -40%;
  left: 50%;
  transform: translateX(-50%) rotate(180deg);
  border-width: 5px;
  border-style: solid;
  border-color: #555 transparent transparent transparent;
}

.tooltip:hover .tooltiptext {
  visibility: visible;
  opacity: 1;
}

.on-main-url {
  background-color: rgb(239, 237, 237);
}


</style>
  