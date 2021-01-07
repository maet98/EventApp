<template>
    <div>
        <ComprasForm />
        <VerCompras />
    </div>
</template>

<script>
import VerCompras from "./VerCompras.vue";
import ComprasForm from "../components/ComprasForm";

export default {
    components: {
        VerCompras,
        ComprasForm
    }
}
</script>
